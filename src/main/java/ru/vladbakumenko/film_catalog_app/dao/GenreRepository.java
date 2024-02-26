package ru.vladbakumenko.film_catalog_app.dao;

import ru.vladbakumenko.film_catalog_app.model.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> findGenresByFilmIds(List<Long> filmIds);
}
