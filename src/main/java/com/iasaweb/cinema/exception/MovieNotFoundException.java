package com.iasaweb.cinema.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(long id) {
        super("Movie with id " + id + "was not found");
    }
}
