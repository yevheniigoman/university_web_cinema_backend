package com.iasaweb.cinema.dto;

import com.iasaweb.cinema.entity.Show;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShowMapper {
    ShowMapper INSTANCE = Mappers.getMapper(ShowMapper.class);

    @Mapping(source = "movie.id", target = "movieId")
    ShowDto showToShowDto(Show show);
}
