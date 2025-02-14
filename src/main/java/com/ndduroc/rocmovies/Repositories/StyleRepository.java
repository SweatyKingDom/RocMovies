package com.ndduroc.rocmovies.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ndduroc.rocmovies.Entity.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {
}