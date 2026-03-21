package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.entity.Genre;
import com.iasaweb.cinema.repository.MovieRepository;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import com.iasaweb.cinema.exception.GenreNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreService genreService;

    public MovieService(MovieRepository movieRepository, GenreService genreRepository) {
        this.movieRepository = movieRepository;
        this.genreService = genreRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) throws MovieNotFoundException {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie update(Long id, Movie updatedMovie)
            throws MovieNotFoundException, GenreNotFoundException {
        Movie currentMovie = findById(id);

        currentMovie.setTitle(updatedMovie.getTitle());
        currentMovie.setDescription(updatedMovie.getDescription());
        currentMovie.setMinutes(updatedMovie.getMinutes());

        Long genreId = updatedMovie.getGenre().getId();
        Genre genre = genreService.findById(genreId);
        currentMovie.setGenre(genre);
        return movieRepository.save(currentMovie);
    }
}
