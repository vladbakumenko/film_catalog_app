package ru.vladbakumenko.film_catalog_app.mapper;

import ru.vladbakumenko.film_catalog_app.dto.ActorDto;
import ru.vladbakumenko.film_catalog_app.dto.FilmDto;
import ru.vladbakumenko.film_catalog_app.dto.GenreDto;
import ru.vladbakumenko.film_catalog_app.model.Actor;
import ru.vladbakumenko.film_catalog_app.model.Film;
import ru.vladbakumenko.film_catalog_app.model.Genre;

import java.util.List;

public class DtoMapper {

    public static FilmDto fromFilmToDto(Film film, List<GenreDto> genres, List<ActorDto> actors) {
        return new FilmDto(
                film.getId(),
                film.getName(),
                film.getYear(),
                film.getDescription(),
                film.getRating(),
                genres,
                actors);
    }

    public static Film fromDtoToFilm(FilmDto filmDto) {
        return new Film(
                filmDto.getId(),
                filmDto.getName(),
                filmDto.getYear(),
                filmDto.getDescription(),
                filmDto.getRating()
        );
    }

    public static GenreDto fromGenreToDto(Genre genre) {
        return new GenreDto(genre.getGenreId(), genre.getNameOfGenre());
    }

    public static Genre fromDtoToGenre(GenreDto dto, Long filmId) {
        return new Genre(filmId, dto.getGenreId(), dto.getNameOfGenre());
    }

    public static ActorDto fromActorToDto(Actor actor) {
        return new ActorDto(actor.getActorId(), actor.getRole(), actor.getFirstName(), actor.getSecondName());
    }
}
