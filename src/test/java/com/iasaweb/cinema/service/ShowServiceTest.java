package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import com.iasaweb.cinema.exception.ShowNotFoundException;
import com.iasaweb.cinema.repository.ShowRepository;
import com.iasaweb.cinema.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private GenreService genreService;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private ShowService showService;

//    @Test
//    void shouldReturnAllMovies() {
//        // Arrange
//        List<Movie> mockMovies = List.of(new Movie(), new Movie());
//
//        when(movieRepository.findAll()).thenReturn(mockMovies);
//
//        // Act
//        List<Movie> result = movieService.findAll();
//
//        // Assert
//        assertEquals(2, result.size()); // Перевіряємо, чи повернулося саме 2 елементи
//        verify(movieRepository).findAll(); // Перевіряємо, чи був виклик бази
//    }

//    @Test
//    void shouldUpdateShowFieldsAndMovie() throws ShowNotFoundException, MovieNotFoundException {
//        // Arrange
//        Long showId = 1L;
//        Long movieId = 10L;
//
//        Show currentShow = new Show();
//        currentShow.setPrice(BigDecimal.valueOf(100.0));
//
//        Movie mockNewMovie = mock(Movie.class);
//        when(mockNewMovie.getId()).thenReturn(movieId);
//
//        // Об'єкт із новими даними для оновлення
//        Show updateData = new Show();
//        updateData.setPrice(BigDecimal.valueOf(250.0));
//        updateData.setMovie(mockNewMovie);
//
//        // Налаштовуємо сценарій взаємодії
//        when(showRepository.findById(showId)).thenReturn(Optional.of(currentShow));
//        when(movieService.findById(movieId)).thenReturn(mockNewMovie);
//        when(showRepository.save(any(Show.class))).thenAnswer(i -> i.getArguments()[0]);
//
//        // Act
//        Show result = showService.update(showId, updateData);
//
//        // Assert
//        assertEquals(BigDecimal.valueOf(250.0), result.getPrice());
//        assertEquals(mockNewMovie, result.getMovie());
//
//        // Перевіряємо виклики залежностей
//        verify(showRepository).findById(showId);
//        verify(movieService).findById(movieId);
//        verify(showRepository).save(currentShow);
//    }
}