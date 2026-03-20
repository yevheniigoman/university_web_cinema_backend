package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.entity.Ticket;
import com.iasaweb.cinema.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/shows/{show_id}/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> all(@PathVariable Long showId) {
        List<Ticket> ticketList = ticketService.findAllAtShow(showId);
        return ResponseEntity.ok(ticketList);
    }

    @GetMapping("/{ticket_id}")
    public ResponseEntity<Ticket> getById(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.findById(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@PathVariable Long showId, @Valid @RequestBody Ticket ticket) {
        Ticket createdTicket = ticketService.create(showId, ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }
}
