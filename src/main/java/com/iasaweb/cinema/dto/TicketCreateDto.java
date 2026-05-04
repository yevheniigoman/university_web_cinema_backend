package com.iasaweb.cinema.dto;

import java.time.LocalDateTime;

public record TicketCreateDto(
    long showId,
    long seatId,
    LocalDateTime purchasedAt
) {}
