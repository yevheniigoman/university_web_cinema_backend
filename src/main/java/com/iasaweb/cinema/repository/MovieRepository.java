package com.iasaweb.cinema.repository;

import com.iasaweb.cinema.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
