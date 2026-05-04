package com.iasaweb.cinema.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShowReadDto(
    MovieDto movie,
    LocalDateTime startTime,
    BigDecimal price
) {}
