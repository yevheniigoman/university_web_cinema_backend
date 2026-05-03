package com.iasaweb.cinema.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Column(name = "purchased_at")
    private LocalDateTime purchasedAt;

    public Ticket() {}

    public Ticket(Show show, Seat seat, LocalDateTime purchasedAt) {
        this.show = show;
        this.seat = seat;
        this.purchasedAt = purchasedAt;
    }

    public Long getId() { return id; }

    public Show getShow() { return show; }
    public void setShow(Show show) { this.show = show; }

    public Seat getSeat() { return seat; }
    public void setSeat(Seat seat) { this.seat = seat; }

    public LocalDateTime getPurchasedAt() { return purchasedAt; }
    public void setPurchasedAt(LocalDateTime purchasedAt) { this.purchasedAt = purchasedAt; }
}