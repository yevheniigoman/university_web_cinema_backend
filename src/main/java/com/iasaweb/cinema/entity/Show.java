package com.iasaweb.cinema.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "price", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    public Show() {}

    public Show(Movie movie, LocalDateTime startTime, BigDecimal price) {
        this.movie = movie;
        this.startTime = startTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
