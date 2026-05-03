package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Ticket;
import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.entity.Seat;
import com.iasaweb.cinema.repository.SeatRepository;
import com.iasaweb.cinema.repository.TicketRepository;
import com.iasaweb.cinema.repository.ShowRepository;
import com.iasaweb.cinema.dto.TicketDto;
import com.iasaweb.cinema.dto.TicketMapper;
import com.iasaweb.cinema.exception.ShowNotFoundException;
import com.iasaweb.cinema.exception.SeatNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<TicketDto> findByShowId(long showId) {
        List<Ticket> ticketList = ticketRepository.findByShowId(showId);
        return ticketList.stream()
                .map(TicketMapper.INSTANCE::ticketToTicketDto)
                .toList();
    }

    public TicketDto findById(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket with id " + ticketId + " not found"));
        return TicketMapper.INSTANCE.ticketToTicketDto(ticket);
    }

    @Transactional
    public void create(TicketDto dto)
            throws ShowNotFoundException, SeatNotFoundException {
        Show show = showRepository.findById(dto.showId())
                .orElseThrow(() -> new ShowNotFoundException(dto.showId()));

        Seat seat = seatRepository.findById(dto.seatId())
                .orElseThrow(() -> new SeatNotFoundException(dto.seatId()));

        Ticket ticket = new Ticket(show, seat, LocalDateTime.now());
        ticketRepository.save(ticket);
    }
}