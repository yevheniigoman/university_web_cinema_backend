package com.iasaweb.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests((requests) -> requests
            .requestMatchers(
                HttpMethod.GET,
                "/movies", "/movies/{id}",
                "/shows", "/shows/{id}",
                "/shows/{show_id}/tickets", "/shows/{show_id}/tickets/{ticket_id}"
            ).permitAll()
            .requestMatchers(
                HttpMethod.POST,
                "/shows/{show_id}/tickets{ticket_id}"
            ).permitAll()
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
