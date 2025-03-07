package com.ndduroc.rocmovies.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ndduroc.rocmovies.Entity.Movie;

public interface IMovieService {

    /** 
     * Liste complète de tous les films
     */
    List<Movie> getListMovies();

    Optional<Movie> getMovieById(long id);

    Movie addMovie(Movie movie);

    Page<Movie> getPaginatedMovies(Pageable pageable);
    Page<Movie> getMoviesByStyleId(Long styleId, Pageable pageable);


}