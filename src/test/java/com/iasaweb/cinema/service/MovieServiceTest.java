package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.entity.Genre;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import com.iasaweb.cinema.exception.GenreNotFoundException;
import com.iasaweb.cinema.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private GenreService genreService;

    @InjectMocks
    private MovieService movieService;

//    @Test
//    void shouldReturnMovieWhenFound() throws MovieNotFoundException {
//        // Arrange
//        Long movieId = 1L;
//        Movie mockMovie = new Movie();
//
//        when(movieRepository.findById(movieId)).thenReturn(Optional.of(mockMovie));
//
//        // Act
//        Movie result = movieService.findById(movieId);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(mockMovie, result); // Перевіряємо, чи повернувся саме той фільм
//        verify(movieRepository).findById(movieId); // Перевіряємо, чи був виклик методу репозиторію
//    }

//    @Test
//    void shouldUpdateMovieFieldsAndGenre() throws MovieNotFoundException, GenreNotFoundException {
//        // 1. Arrange
//        Long movieId = 1L;
//        Long genreId = 5L;
//
//        Genre mockNewGenre = mock(Genre.class);
//        when(mockNewGenre.getId()).thenReturn(genreId);
//
//        Movie oldMovie = new Movie();
//        oldMovie.setTitle("Стара назва");
//
//        // Створюємо дані для оновлення
//        Movie updateData = new Movie();
//        updateData.setTitle("Нова назва");
//        updateData.setDescription("Новий опис");
//        updateData.setMinutes(120);
//        updateData.setGenre(mockNewGenre);
//
//        // Налаштовуємо сценарій:
//        // База фільмів повертає старий фільм
//        when(movieRepository.findById(movieId)).thenReturn(Optional.of(oldMovie));
//        // Сервіс жанрів повертає новий об'єкт жанру
//        when(genreService.findById(genreId)).thenReturn(mockNewGenre);
//        // Збереження повертає результат
//        when(movieRepository.save(any(Movie.class))).thenAnswer(i -> i.getArguments()[0]);
//
//        // Act
//        Movie result = movieService.update(movieId, updateData);
//
//        // Assert
//        // Перевіряємо, чи змістилися текстові поля
//        assertEquals("Нова назва", result.getTitle());
//        assertEquals("Новий опис", result.getDescription());
//        assertEquals(120, result.getMinutes());
//
//        // Перевіряємо, чи підставився правильний жанр
//        assertEquals(mockNewGenre, result.getGenre());
//
//        verify(movieRepository).findById(movieId);
//        verify(genreService).findById(genreId);
//        verify(movieRepository).save(oldMovie);
//    }
}