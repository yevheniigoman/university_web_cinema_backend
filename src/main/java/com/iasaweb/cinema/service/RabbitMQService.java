package com.iasaweb.cinema.service;

import com.iasaweb.cinema.dto.TicketReadDto;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class RabbitMQService {
    private static final String TICKETS_QUEUE = "tickets";
    private final Connection connection;
    private final ObjectMapper objectMapper;

    public RabbitMQService(Connection connection, ObjectMapper objectMapper) {
        this.connection = connection;
        this.objectMapper = objectMapper;
    }

    public void publishTicketCreated(TicketReadDto ticket) {
        try(var channel = connection.createChannel()) {
            channel.queueDeclare(TICKETS_QUEUE, true, false, false, Map.of());
            String payload = objectMapper.writeValueAsString(ticket);
            channel.basicPublish("", TICKETS_QUEUE, null, payload.getBytes());
            System.out.println("Message sent successfully");
            System.out.println("Payload: " + payload);
        } catch (Exception _) {}
    }
}
