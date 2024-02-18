package ru.vladbakumenko.film_catalog_app.dao;

import ru.vladbakumenko.film_catalog_app.dto.FilmAndGenreDto;
import ru.vladbakumenko.film_catalog_app.model.Genre;

import java.util.List;

public interface GenreRepository extends BaseRepository<Genre> {

    List<FilmAndGenreDto> findFilmAndGenreDtoByFilmIds(List<Long> filmIds);
}
