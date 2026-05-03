package com.iasaweb.cinema.dto;

import com.iasaweb.cinema.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    @Mapping(source = "show.id", target = "showId")
    @Mapping(source = "seat.id", target = "seatId")
    TicketDto ticketToTicketDto(Ticket ticket);
}
