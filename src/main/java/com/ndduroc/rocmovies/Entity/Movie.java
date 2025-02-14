package com.ndduroc.rocmovies.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer idMovie;

    @NotBlank
    @Size(max = 100)
    private String title;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    @ManyToOne
    @JoinColumn(name = "productor_id")
    private Productor producedBy;

    @NotNull
    private Integer productionYear;

    @Pattern(regexp = "[A-Z]{2,3}-\\d{6}", message = "La référence doit être au format 'XX-XXXXXX' ou 'XXX-XXXXXX' où X est une lettre majuscule et X est un chiffre.")
    private String reference;

    @Size(max = 1500)
    private String description;

    public Movie() {}

    public Movie(Integer idMovie, String title, Style style, int productionYear, String reference, String description) {
        this.idMovie = idMovie;
        this.title = title;
        this.style = style;
        this.productionYear = productionYear;
        this.reference = reference;
        this.description = description;
    }

    // Getters and setters
    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}