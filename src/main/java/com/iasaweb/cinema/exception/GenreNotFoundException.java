package com.iasaweb.cinema.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(long id) {
        super("Genre with id " + id + "was not found");
    }
}
