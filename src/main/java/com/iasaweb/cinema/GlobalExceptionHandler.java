package com.iasaweb.cinema;

import com.iasaweb.cinema.exception.GenreNotFoundException;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import com.iasaweb.cinema.exception.ShowNotFoundException;
import com.iasaweb.cinema.exception.UserNotFoundException;
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
        UserNotFoundException.class
    })
    public ResponseEntity<Object> handleGenreNotFoundException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
