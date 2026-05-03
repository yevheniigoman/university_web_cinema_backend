package com.iasaweb.cinema.service;

import com.iasaweb.cinema.dto.MovieDto;
import com.iasaweb.cinema.dto.MovieMapper;
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

    public List<MovieDto> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(MovieMapper.INSTANCE::movieToMovieDto)
                .toList();
    }

    public MovieDto findById(Long id) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        return MovieMapper.INSTANCE.movieToMovieDto(movie);
    }

    public void create(MovieDto dto) throws GenreNotFoundException {
        Genre genre = genreService.findById(dto.genreId());
        Movie movie = new Movie(dto.title(), dto.description(), dto.minutes(), genre);
        movieRepository.save(movie);
    }

    public void update(Long id, MovieDto updatedDto)
            throws MovieNotFoundException, GenreNotFoundException {
        Movie currentMovie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        currentMovie.setTitle(updatedDto.title());
        currentMovie.setDescription(updatedDto.description());
        currentMovie.setMinutes(updatedDto.minutes());

        if (currentMovie.getGenre().getId() != updatedDto.genreId()) {
            Genre genre = genreService.findById(updatedDto.genreId());
            currentMovie.setGenre(genre);
        }
        movieRepository.save(currentMovie);
    }
}
