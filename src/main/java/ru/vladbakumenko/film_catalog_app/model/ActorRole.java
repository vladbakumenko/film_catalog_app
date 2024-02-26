package ru.vladbakumenko.film_catalog_app.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActorRole {

    private Long filmId;
    private Long actorId;

    @NotBlank
    private String role;

}
