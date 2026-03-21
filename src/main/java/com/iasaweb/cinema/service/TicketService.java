package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Ticket;
import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.repository.TicketRepository;
import com.iasaweb.cinema.repository.ShowRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;

    public TicketService(TicketRepository ticketRepository, ShowRepository showRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
    }

    // Знайти всі квитки на конкретний сеанс
    public List<Ticket> findAllAtShow(Long showId) {
        return ticketRepository.findByShowId(showId);
    }

    // Знайти квиток за його ID
    public Ticket findById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket with id " + ticketId + " not found"));
    }

    // Створення квитка
    @Transactional
    public Ticket create(Long showId, Ticket ticket) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new RuntimeException("Show with id " + showId + " not found"));

        ticket.setShow(show);
        if (ticket.getPurchasedAt() == null)
            ticket.setPurchasedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }
}