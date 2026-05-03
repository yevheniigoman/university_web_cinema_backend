package com.iasaweb.cinema.exception;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(long id) {
        super("Seat with id " + id + "was not found");
    }
}
