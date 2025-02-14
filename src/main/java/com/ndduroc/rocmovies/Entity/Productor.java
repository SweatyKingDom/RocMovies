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
@Table(name = "productor")
public class Productor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productorId;
    
    @Column(nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "producedBy")
    private List<Movie> movies;
    
}