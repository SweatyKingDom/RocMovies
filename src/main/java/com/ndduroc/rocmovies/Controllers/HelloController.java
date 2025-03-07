package com.ndduroc.rocmovies.Controllers;

import com.ndduroc.rocmovies.Entity.Borrow;
import com.ndduroc.rocmovies.Entity.Customer;
import com.ndduroc.rocmovies.Entity.Movie;
import com.ndduroc.rocmovies.Entity.Style;
import com.ndduroc.rocmovies.Services.IBorrowService;
import com.ndduroc.rocmovies.Services.ICustomerService;
import com.ndduroc.rocmovies.Services.IMovieService;
import com.ndduroc.rocmovies.Services.IStyleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HelloController {

    @Value("${app.welcomeMessage}")
    private String welcomeMessage;

    @Autowired
    @Qualifier("movieService1")
    private IMovieService movieService;

    @Autowired
    private IStyleService styleService;

    @Autowired
    private IBorrowService borrowService;
    
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/")
    public String index(
            @RequestParam(name = "style", required = false) Long styleId,
            @RequestParam(name = "customer", required = false) Long customerId,
            Model model) {
        try {
            // Gestion du filtrage par style pour les films
            List<Movie> movies = movieService.getListMovies();
            Style selectedStyle = null;

            if (styleId != null) {
                Optional<Style> optStyle = styleService.getStyleById(styleId);
                if (optStyle.isPresent()) {
                    selectedStyle = optStyle.get();
                    movies = movies.stream()
                        .filter(movie -> movie.getStyle() != null && movie.getStyle().getStyleId().equals(styleId))
                        .collect(Collectors.toList());
                }
            }

            // Gestion du filtrage par client pour les emprunts
            List<Borrow> borrows;
            Customer selectedCustomer = null;
            
            if (customerId != null) {
                borrows = borrowService.getBorrowsByCustomerId(customerId);
                Optional<Customer> optCustomer = customerService.getCustomerById(customerId);
                if (optCustomer.isPresent()) {
                    selectedCustomer = optCustomer.get();
                }
            } else {
                borrows = borrowService.getAllBorrows();
            }

            // Récupération des listes pour les filtres
            List<Style> allStyles = styleService.getListStyles();
            List<Customer> allCustomers = customerService.getAllCustomers();

            // Ajout des attributs au modèle
            model.addAttribute("welcomeMessage", welcomeMessage);
            model.addAttribute("movies", movies);
            model.addAttribute("selectedStyle", selectedStyle);
            model.addAttribute("allStyles", allStyles);
            model.addAttribute("borrows", borrows);
            model.addAttribute("selectedCustomer", selectedCustomer);
            model.addAttribute("allCustomers", allCustomers);

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
            Movie movie = movieService.getMovieById(id)
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