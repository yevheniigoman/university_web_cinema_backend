package com.iasaweb.cinema.dto;

import java.time.LocalDateTime;

public record TicketReadDto(
    String userEmail,
    ShowReadDto show,
    SeatDto seat,
    LocalDateTime purchasedAt
) {}
