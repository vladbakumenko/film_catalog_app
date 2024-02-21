package ru.vladbakumenko.film_catalog_app.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vladbakumenko.film_catalog_app.dto.FilmDto;
import ru.vladbakumenko.film_catalog_app.model.Film;
import ru.vladbakumenko.film_catalog_app.service.FilmService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public List<FilmDto> getAll() {
        return filmService.getAll();
    }

    @PostMapping
    public Film create(@RequestBody @Valid FilmDto filmDto) {
        return filmService.create(filmDto);
    }
}
