package com.ndduroc.rocmovies.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ndduroc.rocmovies.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByStyleStyleId(Long styleId, Pageable pageable);

}