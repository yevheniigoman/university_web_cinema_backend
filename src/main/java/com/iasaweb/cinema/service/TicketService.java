package com.iasaweb.cinema.service;

import com.iasaweb.cinema.dto.CinemaMapper;
import com.iasaweb.cinema.entity.Ticket;
import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.entity.Seat;
import com.iasaweb.cinema.repository.SeatRepository;
import com.iasaweb.cinema.repository.TicketRepository;
import com.iasaweb.cinema.repository.ShowRepository;
import com.iasaweb.cinema.dto.TicketCreateDto;
import com.iasaweb.cinema.dto.TicketReadDto;
import com.iasaweb.cinema.exception.ShowNotFoundException;
import com.iasaweb.cinema.exception.SeatNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    public TicketService(
        TicketRepository ticketRepository,
        ShowRepository showRepository,
        SeatRepository seatRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
    }

    public List<TicketReadDto> findByShowId(long showId) {
        List<Ticket> ticketList = ticketRepository.findByShowId(showId);
        return ticketList.stream()
                .map(CinemaMapper.INSTANCE::ticketToTicketReadDto)
                .toList();
    }

    public TicketReadDto findById(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket with id " + ticketId + " not found"));
        return CinemaMapper.INSTANCE.ticketToTicketReadDto(ticket);
    }

    @Transactional
    public TicketReadDto create(TicketCreateDto dto)
            throws ShowNotFoundException, SeatNotFoundException {
        Show show = showRepository.findById(dto.showId())
                .orElseThrow(() -> new ShowNotFoundException(dto.showId()));

        Seat seat = seatRepository.findById(dto.seatId())
                .orElseThrow(() -> new SeatNotFoundException(dto.seatId()));

        Ticket ticket = new Ticket(show, seat, LocalDateTime.now());
        ticket = ticketRepository.save(ticket);
        return CinemaMapper.INSTANCE.ticketToTicketReadDto(ticket);
    }
}