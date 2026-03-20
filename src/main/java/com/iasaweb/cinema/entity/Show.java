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
import jakarta.validation.constraints.Null;

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

    @Null
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "price", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    public Show() {}

    public Show(Movie movie, LocalDateTime startTime, BigDecimal price) {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getMinutes());
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getMinutes());
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
