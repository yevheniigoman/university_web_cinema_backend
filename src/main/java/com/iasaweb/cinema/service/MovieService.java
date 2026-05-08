package com.iasaweb.cinema.service;

import com.iasaweb.cinema.dto.CinemaMapper;
import com.iasaweb.cinema.dto.MovieDto;
import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.entity.Genre;
import com.iasaweb.cinema.repository.MovieRepository;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import com.iasaweb.cinema.exception.GenreNotFoundException;
import io.minio.StatObjectResponse;
import io.minio.errors.MinioException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreService genreService;
    private final MinioService minioService;

    public MovieService(MovieRepository movieRepository,
                        GenreService genreRepository,
                        MinioService minioService) {
        this.movieRepository = movieRepository;
        this.genreService = genreRepository;
        this.minioService = minioService;
    }

    public List<MovieDto> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(CinemaMapper.INSTANCE::movieToMovieDto)
                .toList();
    }

    public MovieDto findById(long id) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        return CinemaMapper.INSTANCE.movieToMovieDto(movie);
    }

    public void create(MovieDto dto) throws GenreNotFoundException {
        Genre genre = genreService.findById(dto.genreId());
        Movie movie = new Movie(dto.title(), dto.description(), dto.minutes(), genre);
        movieRepository.save(movie);
    }

    public void update(long id, MovieDto updatedDto)
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

    public void updateImage(long id, MultipartFile file) throws MinioException, IOException {
        minioService.uploadMovieImage(file);

        Movie currentMovie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        currentMovie.setImageUrl(file.getOriginalFilename());
        movieRepository.save(currentMovie);
    }

//    public String getTemporaryImageUrlById(long movieId) throws MinioException {
//        Movie currentMovie = movieRepository.findById(movieId)
//                .orElseThrow(() -> new MovieNotFoundException(movieId));
//        String imageUrl = currentMovie.getImageUrl();
//        return minioService.getTemporaryMovieImageUrl(imageUrl);
//    }
}
