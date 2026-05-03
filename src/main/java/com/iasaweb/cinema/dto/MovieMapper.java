package com.iasaweb.cinema.dto;

import com.iasaweb.cinema.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(source = "genre.id", target = "genreId")
    MovieDto movieToMovieDto(Movie movie);
}
