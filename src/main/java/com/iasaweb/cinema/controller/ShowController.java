package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.dto.ShowCreateDto;
import com.iasaweb.cinema.dto.ShowReadDto;
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
    public ResponseEntity<List<ShowReadDto>> findAll() {
        List<ShowReadDto> showDtoList = showService.findAll();
        return ResponseEntity.ok(showDtoList);
    }

    @GetMapping("/shows/{id}")
    public ResponseEntity<ShowReadDto> findById(@PathVariable("id") Long id)
            throws ShowNotFoundException {
        ShowReadDto showDto = showService.findById(id);
        return ResponseEntity.ok(showDto);
    }

    @PostMapping("/shows")
    public ResponseEntity<Void> create(@Valid @RequestBody ShowCreateDto showDto) {
        showService.create(showDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/shows/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody ShowCreateDto showDto)
            throws ShowNotFoundException, MovieNotFoundException {
        showService.update(id, showDto);
        return ResponseEntity.ok().build();
    }
}
