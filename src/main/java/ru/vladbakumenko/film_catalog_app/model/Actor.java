package ru.vladbakumenko.film_catalog_app.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Actor {

    private Long filmId;
    private Long actorId;

    @NotBlank
    private String role;
    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;
}
