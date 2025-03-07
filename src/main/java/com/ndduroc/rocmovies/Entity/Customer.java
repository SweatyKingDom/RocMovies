package com.ndduroc.rocmovies.Entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
    @Column(nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "customer")
    private List<Borrow> borrows;
    
    // Constructeurs
    public Customer() {}
    
    // Getters et setters
    public Long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Borrow> getBorrows() {
        return borrows;
    }
    
    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }
}