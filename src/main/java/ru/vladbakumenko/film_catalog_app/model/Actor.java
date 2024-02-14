package ru.vladbakumenko.film_catalog_app.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Actor {

    private Long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
