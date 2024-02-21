package ru.vladbakumenko.film_catalog_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.vladbakumenko.film_catalog_app.model.Actor;
import ru.vladbakumenko.film_catalog_app.model.FilmAndGenre;

import java.util.List;

@Data
@AllArgsConstructor
public class FilmDto {

    private Long id;
    @NotBlank
    private String name;
    @Positive
    private Integer year;
    private String description;
    private Float rating;
    transient List<FilmAndGenre> genres;
    transient List<Actor> actors;
}
