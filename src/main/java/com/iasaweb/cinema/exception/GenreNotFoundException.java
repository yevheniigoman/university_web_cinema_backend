package com.iasaweb.cinema.exception;

public class GenreNotFoundException extends Exception {
    public GenreNotFoundException(long id) {
        super("Genre with id " + id + "was not found");
    }
}
