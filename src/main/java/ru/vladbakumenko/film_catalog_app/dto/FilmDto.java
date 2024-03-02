package ru.vladbakumenko.film_catalog_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

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
    @Positive
    private Float rating;
    @NotEmpty
    private List<GenreDto> genres;
    private List<ActorDto> actors;
}
