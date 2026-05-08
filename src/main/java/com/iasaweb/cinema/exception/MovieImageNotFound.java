package com.iasaweb.cinema.exception;

public class MovieImageNotFound extends RuntimeException {
    public MovieImageNotFound(String movieTitle) {
        super("Image for movie '" + movieTitle + "' was not found");
    }
}
