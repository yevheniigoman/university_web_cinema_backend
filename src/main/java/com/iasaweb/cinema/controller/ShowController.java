package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.service.ShowService;
import com.iasaweb.cinema.exception.ShowNotFoundException;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;

@RestController
public class ShowController {
    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/shows")
    public ResponseEntity<List<Show>> findAll() {
        List<Show> showList = showService.findAll();
        return ResponseEntity.ok(showList);
    }

    @GetMapping("/shows/{id}")
    public ResponseEntity<Show> findById(@PathVariable("id") Long id)
            throws ShowNotFoundException {
        Show show = showService.findById(id);
        return ResponseEntity.ok(show);
    }

    @PostMapping("/shows")
    public ResponseEntity<Show> create(@Valid @RequestBody Show show) {
        Show savedShow = showService.create(show);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShow);
    }

    @PutMapping("/shows/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody Show show)
            throws ShowNotFoundException, MovieNotFoundException {
        showService.update(id, show);
        return ResponseEntity.ok().build();
    }
}
