package com.iasaweb.cinema.exception;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(long id) {
        super("Movie with id " + id + "was not found");
    }
}
