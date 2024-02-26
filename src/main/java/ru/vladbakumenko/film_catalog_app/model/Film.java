package ru.vladbakumenko.film_catalog_app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import ru.vladbakumenko.film_catalog_app.dto.ActorDto;
import ru.vladbakumenko.film_catalog_app.dto.GenreDto;

import java.util.List;

@Builder
@Data
public class Film {
    private Long id;
    @NotBlank
    private String name;
    @Positive
    private Integer year;
    private String description;
    @Positive
    private Float rating;
    transient List<GenreDto> genres;
    transient List<ActorDto> actors;
}
