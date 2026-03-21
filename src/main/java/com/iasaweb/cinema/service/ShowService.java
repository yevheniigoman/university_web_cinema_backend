package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import com.iasaweb.cinema.repository.ShowRepository;
import com.iasaweb.cinema.exception.ShowNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShowService {
    private final ShowRepository showRepository;
    private final MovieService movieService;

    public ShowService(ShowRepository showRepository, MovieService movieService) {
        this.showRepository = showRepository;
        this.movieService = movieService;
    }

    public List<Show> findAll() {
        return showRepository.findAll();
    }

    public Show findById(Long id) throws ShowNotFoundException {
        return showRepository.findById(id)
                .orElseThrow(() -> new ShowNotFoundException(id));
    }

    public Show create(Show show) {
        return showRepository.save(show);
    }

    public Show update(Long id, Show updatedShow)
            throws ShowNotFoundException, MovieNotFoundException {
        Show currentShow = findById(id);

        currentShow.setStartTime(updatedShow.getStartTime());
        currentShow.setPrice(updatedShow.getPrice());

        Long movieId = updatedShow.getMovie().getId();
        Movie movie = movieService.findById(movieId);
        currentShow.setMovie(movie);
        return showRepository.save(currentShow);
    }
}
