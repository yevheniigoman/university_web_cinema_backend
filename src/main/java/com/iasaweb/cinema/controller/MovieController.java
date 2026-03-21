package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.service.MovieService;
import com.iasaweb.cinema.exception.GenreNotFoundException;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> movieList = movieService.findAll();
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> findById(@PathVariable("id") Long id)
            throws MovieNotFoundException {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> create(@Valid @RequestBody Movie movie) {
        Movie savedMovie = movieService.create(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody Movie movie)
            throws MovieNotFoundException, GenreNotFoundException {
        movieService.update(id, movie);
        return ResponseEntity.ok().build();
    }
}
