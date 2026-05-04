package com.iasaweb.cinema.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.BeanCreationException;

@Configuration
public class RabbitMQConfig {
    private final String username;
    private final String password;

    public RabbitMQConfig(
        @Value("${rabbitmq.default-user}") String username,
        @Value("${rabbitmq.default-pass}") String password
    ) {
        this.username = username;
        this.password = password;
    }

    @Bean(destroyMethod = "close")
    public Connection rabbitmqConnection() {
        var factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        factory.setUsername(username);
        factory.setPassword(password);

        try {
            return factory.newConnection();
        } catch (Exception e) {
            throw new BeanCreationException("rabbitmqConnection", "\"Failed to connect to RabbitMQ server", e);
        }
    }
}
