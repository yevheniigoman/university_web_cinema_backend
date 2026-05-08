package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.dto.TicketCreateDto;
import com.iasaweb.cinema.dto.TicketReadDto;
import com.iasaweb.cinema.service.RabbitMQService;
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
    private final RabbitMQService rabbitMQService;

    public TicketController(TicketService ticketService, RabbitMQService rabbitMQService) {
        this.ticketService = ticketService;
        this.rabbitMQService = rabbitMQService;
    }

    @GetMapping
    public ResponseEntity<List<TicketReadDto>> all(@PathVariable("show_id") long showId) {
        List<TicketReadDto> ticketDtoList = ticketService.findByShowId(showId);
        return ResponseEntity.ok(ticketDtoList);
    }

    @GetMapping("/{ticket_id}")
    public ResponseEntity<TicketReadDto> getById(@PathVariable("ticket_id") long ticketId) {
        TicketReadDto ticketDto = ticketService.findById(ticketId);
        return ResponseEntity.ok(ticketDto);
    }

    @PostMapping
    public ResponseEntity<Void> create(
        @RequestHeader("Cinema-User") String userEmail,
        @Valid @RequestBody TicketCreateDto ticketDto
    ) {
        TicketReadDto ticketReadDto = ticketService.create(ticketDto, userEmail);
        rabbitMQService.publishTicketCreated(ticketReadDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
