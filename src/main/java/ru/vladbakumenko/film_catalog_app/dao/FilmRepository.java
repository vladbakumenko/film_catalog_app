package ru.vladbakumenko.film_catalog_app.dao;

import ru.vladbakumenko.film_catalog_app.dto.FilmDto;
import ru.vladbakumenko.film_catalog_app.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends BaseRepository<Film> {

    List<Film> findAll();

    List<Film> findByWordInTitle(String word);

    List<Film> findBySecondNameOfActor(String secondName);
}
