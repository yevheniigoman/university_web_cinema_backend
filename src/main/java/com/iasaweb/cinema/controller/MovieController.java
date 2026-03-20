package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.service.MovieService;
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
    public ResponseEntity<List<Movie>> all() {
        List<Movie> movieList = movieService.findAll();
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getById(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> create(@Valid @RequestBody Movie movie) {
        Movie createdMovie = movieService.create(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Movie movie) {
        movieService.update(id, movie);
        return ResponseEntity.noContent().build();
    }
}
