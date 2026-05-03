package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.dto.MovieDto;
import com.iasaweb.cinema.service.MovieService;
import com.iasaweb.cinema.exception.GenreNotFoundException;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> findAll() {
        List<MovieDto> movieDtoList = movieService.findAll();
        return ResponseEntity.ok(movieDtoList);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDto> findById(@PathVariable("id") Long id)
            throws MovieNotFoundException {
        MovieDto movieDto = movieService.findById(id);
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping("/movies")
    public ResponseEntity<Void> create(@Valid @RequestBody MovieDto movieDto)
            throws GenreNotFoundException {
        movieService.create(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody MovieDto movieDto)
            throws MovieNotFoundException, GenreNotFoundException {
        movieService.update(id, movieDto);
        return ResponseEntity.ok().build();
    }
}
