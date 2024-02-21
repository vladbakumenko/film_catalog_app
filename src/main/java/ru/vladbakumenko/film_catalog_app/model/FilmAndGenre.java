package ru.vladbakumenko.film_catalog_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmAndGenre {
    private Long filmId;
    private Integer genreId;
    private String nameOfGenre;
}
