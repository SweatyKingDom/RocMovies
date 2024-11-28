package com.ndduroc.rocmovies.Entity;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Movie {

    @NotNull
    private Long idMovie;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private MovieStyles style;

    @NotNull
    private Integer productionYear;


    @Pattern(regexp = "[A-Z]{2,3}-\\d{6}", message = "La référence doit être au format 'XX-XXXXXX' ou 'XXX-XXXXXX' où X est une lettre majuscule et X est un chiffre.")
    private String reference;

    @Size(max = 1500)
    private String description;

    public Movie() {}

    public Movie(Long idMovie, String title, MovieStyles style, int productionYear, String reference, String description) {
        this.idMovie = idMovie;
        this.title = title;
        this.style = style;
        this.productionYear = productionYear;
        this.reference = reference;
        this.description = description;
    }

    // Getters and setters
    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieStyles getStyle() {
        return style;
    }

    public void setStyle(MovieStyles style) {
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