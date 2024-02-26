package ru.vladbakumenko.film_catalog_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenreDto {

    private Integer genreId;

    @NotBlank
    private String nameOfGenre;
}
