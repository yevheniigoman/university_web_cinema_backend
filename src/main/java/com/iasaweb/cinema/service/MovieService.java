package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.entity.Genre;
import com.iasaweb.cinema.repository.GenreRepository;
import com.iasaweb.cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie with id " + id + " not found"));
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    public void update(Long id, Movie updatedMovie) {
        Movie currentMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie with id " + id + " not found"));

        currentMovie.setTitle(updatedMovie.getTitle());
        currentMovie.setDescription(updatedMovie.getDescription());
        currentMovie.setMinutes(updatedMovie.getMinutes());

        Long genreId = updatedMovie.getGenre().getId();
        Genre genre = genreRepository.findById(genreId)
                        .orElseThrow(() -> new RuntimeException("Genre with id " + genreId + " not found"));
        currentMovie.setGenre(genre);
        movieRepository.save(currentMovie);
    }
}
