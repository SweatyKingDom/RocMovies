package com.ndduroc.rocmovies.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ndduroc.rocmovies.Entity.Customer;
import com.ndduroc.rocmovies.Repositories.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        System.out.println("Création du service CustomerService");
    }
    
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
}