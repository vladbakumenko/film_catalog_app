package ru.vladbakumenko.film_catalog_app.dao;

import ru.vladbakumenko.film_catalog_app.model.Film;

import java.util.List;

public interface FilmRepository extends BaseRepository<Film> {

    List<Film> findAll();

    List<Film> findByWordInTitle(String word);

    List<Film> findBySecondNameOfActor(String secondName);
}
