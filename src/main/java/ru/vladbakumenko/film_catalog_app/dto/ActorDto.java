package ru.vladbakumenko.film_catalog_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActorDto {

    private Long actorId;

    @NotBlank
    private String role;
    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;
}
