package com.ndduroc.rocmovies.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ndduroc.rocmovies.Repositories.StyleRepository;
import com.ndduroc.rocmovies.Entity.Style;

@Service
public class StyleService implements IStyleService {
    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
        System.out.println("Création du service StyleService");
    }

    @Override
    public List<Style> getListStyles() {
        return (List<Style>) styleRepository.findAll();
    }
    
    @Override
    public Optional<Style> getStyleById(Long id) {
        return styleRepository.findById(id);
    }
}