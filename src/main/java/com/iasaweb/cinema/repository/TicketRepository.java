package com.iasaweb.cinema.repository;

import com.iasaweb.cinema.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByShowId(Long showId);
}
