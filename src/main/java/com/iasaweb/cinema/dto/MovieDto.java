package com.iasaweb.cinema.dto;

public record MovieDto(
    String title,
    String description,
    int minutes,
    long genreId
) {}