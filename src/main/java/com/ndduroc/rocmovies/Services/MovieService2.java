package com.ndduroc.rocmovies.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.ndduroc.rocmovies.Entity.Movie;
import com.ndduroc.rocmovies.Entity.MovieStyles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("preprod")
public class MovieService2 implements IMovieService {
    
    private List<Movie> movieList;
    private final AtomicLong idCounter = new AtomicLong();

    /**
     * Fournit une liste de films 'en dur' 
     * en attendant de pouvoir utiliser une base de données 
     * @return
     */
    private List<Movie> getDefaultList() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(idCounter.incrementAndGet(), "Cloud Atlas", MovieStyles.SF, 2012, "CA-000001", "Un film de SF sur les points virgules"));
        movies.add(new Movie(idCounter.incrementAndGet(), "Shutter Island", MovieStyles.THRILLER, 2010, "SI-000002", "Un film de thriller sur le mcdo"));
        movies.add(new Movie(idCounter.incrementAndGet(), "Interstellar", MovieStyles.SF, 2018, "IN-000003", "Un film de SF sur les arbres"));
        movies.add(new Movie(idCounter.incrementAndGet(), "Pulp Fiction", MovieStyles.ACTION, 2001, "PF-000004", "Un film d'action sur le Z"));
        movies.add(new Movie(idCounter.incrementAndGet(), "Mulholland Drive", MovieStyles.THRILLER, 2001, "MD-000005", "Un film de thriller sur le Z"));
        
        return movies;
    }

    /** 
     * Liste complète de tous les films
     */
    @Override
    public List<Movie> getListMovies() {
        if (movieList == null) {
            movieList = getDefaultList();
        }
        return movieList;
    }

    @Override
    public Optional<Movie> getMovieById(long id) {
        return getListMovies().stream().filter(m -> m.getIdMovie() == id).findFirst();
    }

    public MovieService2() {
        System.out.println("Création du service MovieService");
    }
    // public void addMovie(Movie movie) {
    //     getListMovies().add(movie);
    // }
    @Override
    public Movie addMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Le film ne peut pas être null");
        }
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre du film ne peut pas être vide");
        }
        if (movie.getStyle() == null) {
            throw new IllegalArgumentException("Le style du film ne peut pas être null");
        }
        if (movie.getProductionYear() <= 0) {
            throw new IllegalArgumentException("L'année de production doit être positive");
        }
    
        movie.setIdMovie(idCounter.incrementAndGet());
        getListMovies().add(movie);
        return movie;
    }

}