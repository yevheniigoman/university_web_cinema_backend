package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.dto.TicketDto;
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
    public ResponseEntity<List<TicketDto>> all(@PathVariable("show_id") long showId) {
        List<TicketDto> ticketDtoList = ticketService.findByShowId(showId);
        return ResponseEntity.ok(ticketDtoList);
    }

    @GetMapping("/{ticket_id}")
    public ResponseEntity<TicketDto> getById(@PathVariable("ticket_id") long ticketId) {
        TicketDto ticketDto = ticketService.findById(ticketId);
        return ResponseEntity.ok(ticketDto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody TicketDto ticketDto) {
        ticketService.create(ticketDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
