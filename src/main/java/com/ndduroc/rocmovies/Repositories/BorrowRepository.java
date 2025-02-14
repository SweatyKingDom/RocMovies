package com.ndduroc.rocmovies.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndduroc.rocmovies.Entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}