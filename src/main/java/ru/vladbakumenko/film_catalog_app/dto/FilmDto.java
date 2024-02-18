package ru.vladbakumenko.film_catalog_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.vladbakumenko.film_catalog_app.model.Actor;

import java.util.List;

@Data
@AllArgsConstructor
public class FilmDto {

    private Long id;
    private String name;
    private Integer year;
    private String description;
    private Float rating;
    transient List<FilmAndGenreDto> genres;
    transient List<Actor> actors;
}
