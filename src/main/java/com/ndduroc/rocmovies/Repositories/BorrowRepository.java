package com.ndduroc.rocmovies.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ndduroc.rocmovies.Entity.Borrow;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByCustomerCustomerId(Long customerId);
}