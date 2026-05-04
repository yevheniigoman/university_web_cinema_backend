package com.iasaweb.cinema.dto;

import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.entity.Seat;
import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CinemaMapper {
    CinemaMapper INSTANCE = Mappers.getMapper(CinemaMapper.class);

//    @Mapping(source = "show.id", target = "showId")
//    @Mapping(source = "seat.id", target = "seatId")
//    TicketCreateDto ticketToTicketCreateDto(Ticket ticket);

    TicketReadDto ticketToTicketReadDto(Ticket ticket);

    SeatDto seatToSeatDto(Seat seat);

    @Mapping(source = "genre.id", target = "genreId")
    MovieDto movieToMovieDto(Movie movie);

//    @Mapping(source = "movie.id", target = "movieId")
    ShowReadDto showToShowReadDto(Show show);
}
