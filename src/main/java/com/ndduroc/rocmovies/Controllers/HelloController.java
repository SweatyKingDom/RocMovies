package com.ndduroc.rocmovies.Controllers;

import com.ndduroc.rocmovies.Entity.Movie;
import com.ndduroc.rocmovies.Entity.MovieStyles;
import com.ndduroc.rocmovies.Services.IMovieService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
@RequestMapping("/")
public class HelloController {

    @Value("${app.welcomeMessage}")
    private String welcomeMessage;

    @Autowired
    @Qualifier("movieService1")
    private IMovieService movieService;

    @GetMapping("/")
    public String index(@RequestParam(name = "style", required = false) MovieStyles style, Model model) {
        try {
            List<Movie> movies = movieService.getListMovies();
            if (style != null) {
                movies = movies.stream() // Convertit la liste des films en un flux
                .filter(movie -> movie.getStyle() == style) // Filtre les films par style
                .collect(Collectors.toList()); // Collecte les films filtrés dans une nouvelle liste
            }
            model.addAttribute("welcomeMessage", welcomeMessage);
            model.addAttribute("movies", movies);
            model.addAttribute("selectedStyle", style);
            model.addAttribute("allStyles", MovieStyles.values());
            return "hello";
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Erreur lors du chargement de la page d'accueil", 
                e
            );
        }
    }
    
    @GetMapping("/movie/{id}")
    public String getMovieById(@PathVariable long id, Model model) {
        try {
            var movie = movieService.getMovieById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Film non trouvé avec l'ID: " + id
                ));
            model.addAttribute("movie", movie);
            return "movie";
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Erreur lors du chargement du film", 
                e
            );
        }
    }
}