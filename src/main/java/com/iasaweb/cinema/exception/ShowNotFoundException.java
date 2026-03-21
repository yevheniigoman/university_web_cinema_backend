package com.iasaweb.cinema.exception;

public class ShowNotFoundException extends Exception {
    public ShowNotFoundException(long id) {
        super("Show with id " + id + "was not found");
    }
}
