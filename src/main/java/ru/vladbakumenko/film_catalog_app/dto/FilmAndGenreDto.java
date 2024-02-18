package ru.vladbakumenko.film_catalog_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmAndGenreDto {
    private Long filmId;
    private Integer genreId;
    private String nameOfGenre;
}
