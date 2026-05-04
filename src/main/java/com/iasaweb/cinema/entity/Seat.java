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

    public long getId() { return id; }

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getPlace() { return place; }
    public void setPlace(int place) { this.place = place; }
}