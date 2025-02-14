package com.ndduroc.rocmovies.Services;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import com.ndduroc.rocmovies.Entity.Borrow; 
import com.ndduroc.rocmovies.Repositories.BorrowRepository;

@Service public class BorrowService implements IBorrowService {
    private final BorrowRepository borrowRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
        System.out.println("Création du service BorrowService");
    }
    
    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }
}