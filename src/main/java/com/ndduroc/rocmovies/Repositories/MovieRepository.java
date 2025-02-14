package com.ndduroc.rocmovies.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ndduroc.rocmovies.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}