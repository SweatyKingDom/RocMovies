package com.ndduroc.rocmovies.Services;

import java.util.List;
import java.util.Optional;
import com.ndduroc.rocmovies.Entity.Movie;
import com.ndduroc.rocmovies.Repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service("movieService1")
public class MovieService1 implements IMovieService {

    private final MovieRepository movieRepository;

    public MovieService1(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        System.out.println("Création du service MovieService1");
    }

    @Override
    public List<Movie> getListMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(long id) {
        return movieRepository.findById(id);
    }      
    
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

        return movie;
    }
}