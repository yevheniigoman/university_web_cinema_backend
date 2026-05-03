package com.iasaweb.cinema.dto;

import java.time.LocalDateTime;

public record TicketDto(long showId, long seatId, LocalDateTime purchasedAt) {}
