package com.iasaweb.cinema.controller;

import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.dto.ShowDto;
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
    public ResponseEntity<List<ShowDto>> findAll() {
        List<ShowDto> showDtoList = showService.findAll();
        return ResponseEntity.ok(showDtoList);
    }

    @GetMapping("/shows/{id}")
    public ResponseEntity<ShowDto> findById(@PathVariable("id") Long id)
            throws ShowNotFoundException {
        ShowDto showDto = showService.findById(id);
        return ResponseEntity.ok(showDto);
    }

    @PostMapping("/shows")
    public ResponseEntity<Void> create(@Valid @RequestBody ShowDto showDto) {
        showService.create(showDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/shows/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody ShowDto showDto)
            throws ShowNotFoundException, MovieNotFoundException {
        showService.update(id, showDto);
        return ResponseEntity.ok().build();
    }
}
