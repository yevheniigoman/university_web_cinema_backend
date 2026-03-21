package com.iasaweb.cinema.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row", nullable = false)
    private Integer row;

    @Column(name = "place", nullable = false)
    private Integer place;

    public Seat() {}

    public Seat(Integer row, Integer place) {
        this.row = row;
        this.place = place;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public Integer getRow() { return row; }
    public void setRow(Integer row) { this.row = row; }

    public Integer getPlace() { return place; }
    public void setPlace(Integer place) { this.place = place; }
}