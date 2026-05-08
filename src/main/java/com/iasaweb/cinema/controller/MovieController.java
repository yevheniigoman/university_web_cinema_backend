package com.iasaweb.cinema.controller;

import com.google.common.net.HttpHeaders;
import com.iasaweb.cinema.dto.MovieDto;
import com.iasaweb.cinema.service.MinioService;
import com.iasaweb.cinema.service.MovieService;
import com.iasaweb.cinema.exception.GenreNotFoundException;
import com.iasaweb.cinema.exception.MovieNotFoundException;

import io.minio.StatObjectResponse;
import io.minio.errors.MinioException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import java.io.IOException;

@RestController
public class MovieController {
    private final MovieService movieService;
    private final MinioService minioService;

    public MovieController(MovieService movieService, MinioService minioService) {
        this.movieService = movieService;
        this.minioService = minioService;
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
    public ResponseEntity<Void> update(
        @PathVariable("id") Long id,
        @Valid @RequestBody MovieDto movieDto
    ) throws MovieNotFoundException, GenreNotFoundException {
        movieService.update(id, movieDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/movies/{id}/image")
    public ResponseEntity<Void> setImage(@PathVariable("id") Long id,
                                         @RequestParam("file") MultipartFile file)
            throws MinioException, IOException {
        movieService.updateImage(id, file);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/movies/{id}/image")
//    public ResponseEntity<Map<String, String>> getImageUrl(@PathVariable("id") Long movieId)
//            throws MinioException {
//        String imageUrl = movieService.getTemporaryImageUrlById(movieId);
//        return ResponseEntity.ok().body(Map.of("imageUrl", imageUrl));
//    }

    @GetMapping("/movies/{id}/image")
    public ResponseEntity<byte[]> getImageUrl(@PathVariable("id") Long id) throws MinioException, IOException {
        MovieDto movie = movieService.findById(id);
        if (movie.imageUrl().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StatObjectResponse metadata = minioService.getMovieImageMetadata(movie);
        InputStream stream = minioService.getMovieImage(movie.imageUrl());

        String filename = "movie_image";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentLength(metadata.size())
                .contentType(MediaType.valueOf(metadata.contentType()))
                .body(stream.readAllBytes());
    }
}
