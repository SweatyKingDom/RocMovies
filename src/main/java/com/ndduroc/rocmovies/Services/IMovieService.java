package com.ndduroc.rocmovies.Services;

import java.util.List;
import java.util.Optional;

import com.ndduroc.rocmovies.Entity.Movie;

public interface IMovieService {

    /** 
     * Liste complète de tous les films
     */
    List<Movie> getListMovies();

    Optional<Movie> getMovieById(long id);

    // public void addMovie(Movie movie) {
    //     getListMovies().add(movie);
    // }
    Movie addMovie(Movie movie);

}