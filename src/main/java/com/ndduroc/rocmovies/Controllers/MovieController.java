package com.ndduroc.rocmovies.Controllers;

import com.ndduroc.rocmovies.Entity.Movie;
import com.ndduroc.rocmovies.Entity.MovieStyles;
import com.ndduroc.rocmovies.Services.IMovieService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    @Qualifier("movieService1")
    private IMovieService movieService;
    // @Qualifier("movieService2")
    // private IMovieService movieService2;

    @GetMapping
    public List<Movie> getAllMovies(@RequestParam(name = "style", required = false) MovieStyles style) {
        try {
            List<Movie> list = movieService.getListMovies();
            if (style != null) {
                return list.stream()
                          .filter(movie -> movie.getStyle() == style)
                          .collect(Collectors.toList());
            }
            return list;
        } 
        catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Erreur lors de la récupération des films", 
                e
            );
        }
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable long id) {
        try {
            return movieService.getMovieById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Film non trouvé avec l'ID: " + id
                ));
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Erreur lors de la récupération du film", 
                e
            );
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie addedMovie = movieService.addMovie(movie);
            return new ResponseEntity<>(addedMovie, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur inattendue s'est produite lors de l'ajout du film.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}