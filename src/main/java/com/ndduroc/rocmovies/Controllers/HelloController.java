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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Set; 
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
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size,
            Model model) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            
            Style selectedStyle = null;
            Customer selectedCustomer = null;
            Page<Movie> moviePage;
            
            if (styleId != null) {
                Optional<Style> optStyle = styleService.getStyleById(styleId);
                if (optStyle.isPresent()) {
                    selectedStyle = optStyle.get();
                    moviePage = movieService.getMoviesByStyleId(styleId, pageable);
                } else {
                    moviePage = movieService.getPaginatedMovies(pageable);
                }
            } else {
                moviePage = movieService.getPaginatedMovies(pageable);
            }
            
            List<Movie> movies = moviePage.getContent();
            
            List<Borrow> borrows = borrowService.getAllBorrows();
            
            if (customerId != null) {
                Optional<Customer> optCustomer = customerService.getCustomerById(customerId);
                if (optCustomer.isPresent()) {
                    selectedCustomer = optCustomer.get();
                    borrows = borrowService.getBorrowsByCustomerId(customerId);
                    
                    if (selectedStyle != null) {
                        Set<Object> borrowedMovieIds = borrows.stream()
                            .map(b -> b.getMovie().getIdMovie())
                            .collect(Collectors.toSet());
                        
                        movies = movies.stream()
                            .filter(movie -> borrowedMovieIds.contains(movie.getIdMovie()))
                            .collect(Collectors.toList());
                    }
                }
            }
            
            List<Style> allStyles = styleService.getListStyles();
            List<Customer> allCustomers = customerService.getAllCustomers();
            
            model.addAttribute("movies", movies);
            model.addAttribute("welcomeMessage", welcomeMessage);
            model.addAttribute("selectedStyle", selectedStyle);
            model.addAttribute("allStyles", allStyles);
            model.addAttribute("borrows", borrows);
            model.addAttribute("selectedCustomer", selectedCustomer);
            model.addAttribute("allCustomers", allCustomers);
            
            model.addAttribute("styleId", styleId);
            model.addAttribute("customerId", customerId);
            
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", moviePage.getTotalPages());
            model.addAttribute("totalItems", moviePage.getTotalElements());
            model.addAttribute("size", size);
            
            return "hello";
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erreur lors du chargement de la page d'accueil: " + e.getMessage(),
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