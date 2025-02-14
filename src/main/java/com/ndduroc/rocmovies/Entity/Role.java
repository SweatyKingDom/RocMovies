package com.ndduroc.rocmovies.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    
    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;
    
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    
    @Size(max = 1500)
    private String role;
    
}
   