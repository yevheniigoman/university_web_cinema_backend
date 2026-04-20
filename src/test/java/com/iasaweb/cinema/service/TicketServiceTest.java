package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.entity.Ticket;
import com.iasaweb.cinema.repository.ShowRepository;
import com.iasaweb.cinema.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private ShowRepository showRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void shouldCreateTicketWithCurrentTime() {
        // Arrange
        Long showId = 1L;
        Show mockShow = new Show();
        Ticket inputTicket = new Ticket();

        when(showRepository.findById(showId)).thenReturn(Optional.of(mockShow));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Ticket result = ticketService.create(showId, inputTicket);

        // Assert
        assertNotNull(result.getPurchasedAt()); // Чи поставилася дата покупки?
        assertEquals(mockShow, result.getShow()); // Чи прив'язався правильний сеанс?
        verify(ticketRepository, times(1)).save(any()); // Чи викликався метод збереження рівно 1 раз?
    }

    @Test
    void shouldThrowExceptionWhenShowNotFound() {
        // Arrange
        Long showId = 99L;

        when(showRepository.findById(showId)).thenReturn(Optional.empty());

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
        {
            ticketService.create(showId, new Ticket());
        });

        // Assert
        assertEquals("Show with id 99 not found", exception.getMessage());
    }

    @Test
    void shouldReturnTicketWhenFound() {
        // Arrange
        Long ticketId = 10L;
        Ticket mockTicket = new Ticket();

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(mockTicket));

        // Act
        Ticket result = ticketService.findById(ticketId);

        // Assert
        assertNotNull(result);
        assertEquals(mockTicket, result);
        verify(ticketRepository).findById(ticketId);
    }
}