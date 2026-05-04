package com.iasaweb.cinema.dto;

import java.time.LocalDateTime;

public record TicketReadDto(
    ShowReadDto show,
    SeatDto seat,
    LocalDateTime purchasedAt
) {}
