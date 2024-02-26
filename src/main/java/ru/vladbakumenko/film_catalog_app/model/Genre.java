package ru.vladbakumenko.film_catalog_app.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {

    private transient Long filmId;
    private Integer genreId;

    @NotBlank
    private String nameOfGenre;
}
