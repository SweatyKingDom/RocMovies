package com.ndduroc.rocmovies.Services;

import java.util.List;
import com.ndduroc.rocmovies.Entity.Borrow;

public interface IBorrowService {
    List<Borrow> getAllBorrows();
}