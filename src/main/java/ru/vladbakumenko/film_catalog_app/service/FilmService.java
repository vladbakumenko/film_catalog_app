package ru.vladbakumenko.film_catalog_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vladbakumenko.film_catalog_app.dao.impl.FilmRepositoryImpl;
import ru.vladbakumenko.film_catalog_app.dto.FilmDto;
import ru.vladbakumenko.film_catalog_app.mapper.DtoMapper;
import ru.vladbakumenko.film_catalog_app.model.Actor;
import ru.vladbakumenko.film_catalog_app.model.Film;
import ru.vladbakumenko.film_catalog_app.model.Genre;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final FilmRepositoryImpl filmRepository;
    private final GenreService genreService;

    private final ActorService actorService;

    public List<FilmDto> getAll() {
        List<Film> films = filmRepository.findAll();

        var ids = films.stream().map(Film::getId).toList();

        Map<Long, List<Genre>> filmsAndGenres =
                genreService.findGenresByFilmIds(ids);

        Map<Long, List<Actor>> filmsAndActors =
                actorService.findActorsByFilmIds(ids);

        return films.stream().map(film -> {
            var genres = filmsAndGenres.get(film.getId()).stream()
                    .map(DtoMapper::fromGenreToDto)
                    .toList();

            var actors = filmsAndActors.get(film.getId()).stream()
                    .map(DtoMapper::fromActorToDto)
                    .toList();

            return DtoMapper.fromFilmToDto(film, genres, actors);
        }).toList();
    }

    //todo add mapper
    public Film create(FilmDto filmDto) {
        throwIfFilmNotValid(filmDto);
        Film film = Film.builder()
                .name(filmDto.getName())
                .year(filmDto.getYear())
                .description(filmDto.getDescription())
                .rating(filmDto.getRating())
                .genres(filmDto.getGenres())
                .actors(filmDto.getActors())
                .build();
        return filmRepository.create(film);
    }

    private void throwIfFilmNotValid(FilmDto filmDto) {
    }
}
