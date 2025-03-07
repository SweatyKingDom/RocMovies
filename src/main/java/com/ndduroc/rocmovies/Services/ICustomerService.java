package com.ndduroc.rocmovies.Services;

import java.util.List;
import java.util.Optional;
import com.ndduroc.rocmovies.Entity.Customer;

public interface ICustomerService {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
}