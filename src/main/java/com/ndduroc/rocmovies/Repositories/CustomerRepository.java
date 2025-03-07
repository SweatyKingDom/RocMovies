package com.ndduroc.rocmovies.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ndduroc.rocmovies.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}