package com.iasaweb.cinema.repository;

import com.iasaweb.cinema.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
