package com.iasaweb.cinema.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShowCreateDto(
    long movieId,
    LocalDateTime startTime,
    BigDecimal price
) {}
