package com.ndduroc.rocmovies.Services;

import java.util.List;
import java.util.Optional;
import com.ndduroc.rocmovies.Entity.Style;

public interface IStyleService {
    Optional<Style> getStyleById(Long id);
    List<Style> getListStyles();
}