package com.iasaweb.cinema.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShowDto(
    long movieId,
    LocalDateTime startTime,
    BigDecimal price
) {}
