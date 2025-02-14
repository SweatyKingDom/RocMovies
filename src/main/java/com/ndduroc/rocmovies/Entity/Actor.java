package com.ndduroc.rocmovies.Entity;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String firstName;
    
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    
    @OneToMany(mappedBy = "actor")
    private List<Role> roles;
}
