package com.ndduroc.rocmovies.Services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.ndduroc.rocmovies.Entity.Borrow;
import com.ndduroc.rocmovies.Entity.Customer;
import com.ndduroc.rocmovies.Entity.Movie;
import com.ndduroc.rocmovies.Repositories.BorrowRepository;
import com.ndduroc.rocmovies.Repositories.MovieRepository;
import com.ndduroc.rocmovies.Repositories.CustomerRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("movieService1")
public class MovieService1 implements IMovieService {

    private final MovieRepository movieRepository;
    private final BorrowRepository borrowRepository;
    private final CustomerRepository customerRepository;
    private final Random random = new Random();
    
    public MovieService1(MovieRepository movieRepository, BorrowRepository borrowRepository, CustomerRepository customerRepository) {
        this.movieRepository = movieRepository;
        this.borrowRepository = borrowRepository;
        this.customerRepository = customerRepository;
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
    public Page<Movie> getPaginatedMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Page<Movie> getMoviesByStyleId(Long styleId, Pageable pageable) {
        return movieRepository.findByStyleStyleId(styleId, pageable);
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

    @Transactional
    public boolean transferBorrows(Long sourceCustomerId, Long targetCustomerId) {
        // check que les customer existent
        if (sourceCustomerId == null || targetCustomerId == null) {
            throw new IllegalArgumentException("Les IDs d'abonnés ne peuvent pas être null");
        }
        // check que les customet sont différent
        if (sourceCustomerId.equals(targetCustomerId)) {
            throw new IllegalArgumentException("Les IDs d'abonnés source et cible doivent être différents");
        }
        
        // Simuler un échec aléatoire (50% de chance d''échec)
        if (random.nextInt(2) == 0) {
            throw new RuntimeException("Échec simulé du transfert d'emprunts");
        }
        
        List<Borrow> borrowsToTransfer = borrowRepository.findByCustomerCustomerId(sourceCustomerId);
        
        if (borrowsToTransfer.isEmpty()) {
            return true;
        }
        
        // Récupérer l'abonné cible pour vérifier son zxistence
        Customer targetCustomer = customerRepository.findById(targetCustomerId)
            .orElseThrow(() -> new IllegalArgumentException("Abonné cible non trouvé"));
        
        // Effectuer le transfrt de chaque emprunt
        for (Borrow borrow : borrowsToTransfer) {
            borrow.setCustomer(targetCustomer);
            borrowRepository.save(borrow);
        }
        
        return true;
    }
}