//package com.iasaweb.cinema.service;
//
//import com.iasaweb.cinema.entity.Genre;
//import com.iasaweb.cinema.exception.GenreNotFoundException;
//import com.iasaweb.cinema.repository.GenreRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class GenreServiceTest {
//
//    @Mock
//    private GenreRepository genreRepository;
//
//    @InjectMocks
//    private GenreService genreService;
//
//    @Test
//    void shouldReturnGenreWhenFound() throws GenreNotFoundException {
//        // Arrange
//        Long genreId = 1L;
//        Genre mockGenre = new Genre();
//
//        when(genreRepository.findById(genreId)).thenReturn(Optional.of(mockGenre));
//
//        // Act
//        Genre result = genreService.findById(genreId);
//
//        // Assert
//        assertNotNull(result); // Перевіряємо, що результат не порожній
//        assertEquals(mockGenre, result); // Перевіряємо, що це саме наш жанр
//        verify(genreRepository).findById(genreId); // Перевіряємо, чи був виклик бази
//    }
//}