package com.iasaweb.cinema;

import com.iasaweb.cinema.exception.GenreNotFoundException;
import com.iasaweb.cinema.exception.MovieImageNotFound;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import com.iasaweb.cinema.exception.ShowNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import java.util.Map;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
        GenreNotFoundException.class,
        MovieNotFoundException.class,
        ShowNotFoundException.class,
        MovieImageNotFound.class
    })
    public ResponseEntity<Object> handleGenreNotFoundException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("msg", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex, WebRequest request) {
        System.out.println(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(Map.of("msg", ex.getMessage()));
    }
}
