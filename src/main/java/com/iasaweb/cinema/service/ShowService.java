package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.Show;
import com.iasaweb.cinema.entity.Movie;
import com.iasaweb.cinema.dto.ShowReadDto;
import com.iasaweb.cinema.dto.ShowCreateDto;
import com.iasaweb.cinema.dto.CinemaMapper;
import com.iasaweb.cinema.repository.MovieRepository;
import com.iasaweb.cinema.repository.ShowRepository;
import com.iasaweb.cinema.exception.MovieNotFoundException;
import com.iasaweb.cinema.exception.ShowNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShowService {
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
    }

    public List<ShowReadDto> findAll() {
        List<Show> showList = showRepository.findAll();
        return showList.stream().map(CinemaMapper.INSTANCE::showToShowReadDto).toList();
    }

    public ShowReadDto findById(Long id) throws ShowNotFoundException {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new ShowNotFoundException(id));
        return CinemaMapper.INSTANCE.showToShowReadDto(show);
    }

    public void create(ShowCreateDto dto) {
        Movie movie = movieRepository.findById(dto.movieId())
                .orElseThrow(() -> new MovieNotFoundException(dto.movieId()));
        Show show = new Show(movie, dto.startTime(), dto.price());
        showRepository.save(show);
    }

    public void update(Long id, ShowCreateDto updatedDto)
            throws ShowNotFoundException, MovieNotFoundException {
        Show currentShow = showRepository.findById(id)
                .orElseThrow(() -> new ShowNotFoundException(id));

        currentShow.setStartTime(updatedDto.startTime());
        currentShow.setPrice(updatedDto.price());

        long movieId = updatedDto.movieId();
        if (currentShow.getMovie().getId() != movieId) {
            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new MovieNotFoundException(movieId));
            currentShow.setMovie(movie);
        }
        showRepository.save(currentShow);
    }
}
