package ru.vladbakumenko.film_catalog_app.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {

    private Long filmId;
    private Integer genreId;

    @NotBlank
    private transient String nameOfGenre;
}
