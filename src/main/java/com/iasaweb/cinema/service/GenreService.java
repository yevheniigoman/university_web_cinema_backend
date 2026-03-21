package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Genre;
import com.iasaweb.cinema.repository.GenreRepository;
import com.iasaweb.cinema.exception.GenreNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre findById(Long id) throws GenreNotFoundException {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }
}
