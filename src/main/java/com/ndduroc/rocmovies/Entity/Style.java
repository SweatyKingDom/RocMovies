package com.ndduroc.rocmovies.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// public enum Style {
//     SF,
//     DRAME,
//     THRILLER,
//     ACTION
// }

// Style.java
@Entity
@Table(name = "style")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long styleId;
    
    @Column(nullable = false)
    private String styleName;
    
    @OneToMany(mappedBy = "style")
    private List<Movie> movies;
    
    public Long getStyleId() {
        return styleId;
    }
    public String getStyleName() {
        return styleName;
    }
    
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
    
}