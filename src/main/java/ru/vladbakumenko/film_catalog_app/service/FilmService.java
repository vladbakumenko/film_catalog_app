package ru.vladbakumenko.film_catalog_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladbakumenko.film_catalog_app.dao.impl.FilmRepositoryImpl;
import ru.vladbakumenko.film_catalog_app.dto.ActorDto;
import ru.vladbakumenko.film_catalog_app.dto.FilmDto;
import ru.vladbakumenko.film_catalog_app.dto.GenreDto;
import ru.vladbakumenko.film_catalog_app.exception.BadRequestException;
import ru.vladbakumenko.film_catalog_app.mapper.DtoMapper;
import ru.vladbakumenko.film_catalog_app.model.Actor;
import ru.vladbakumenko.film_catalog_app.model.Film;
import ru.vladbakumenko.film_catalog_app.model.Genre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final FilmRepositoryImpl filmRepository;
    private final GenreService genreService;

    private final ActorService actorService;

    @Transactional
    public List<FilmDto> getAll() {
        List<Film> films = filmRepository.findAll();

        var ids = films.parallelStream().map(Film::getId).toList();

        Map<Long, List<Genre>> filmsAndGenres =
                genreService.findGenresByFilmIds(ids);

        Map<Long, List<Actor>> filmsAndActors =
                actorService.findActorsByFilmIds(ids);

        return films.parallelStream().map(film -> {
            var genres = filmsAndGenres.get(film.getId()).stream()
                    .map(DtoMapper::fromGenreToDto)
                    .toList();

            List<ActorDto> actors = new ArrayList<>();
            if (!filmsAndActors.isEmpty() && filmsAndActors.get(film.getId()) != null) {
                actors = filmsAndActors.get(film.getId()).stream()
                        .map(DtoMapper::fromActorToDto)
                        .toList();
            }

            return DtoMapper.fromFilmToDto(film, genres, actors);
        }).toList();
    }

    //todo add mapper
    @Transactional
    public FilmDto create(FilmDto filmDto) {
        throwIfFilmNotValid(filmDto);
        Film newFilm = filmRepository.create(DtoMapper.fromDtoToFilm(filmDto));
        List<GenreDto> genres = genreService.create(filmDto.getGenres(), newFilm.getId());
        List<ActorDto> actors = filmDto.getActors() != null ?
                actorService.create(filmDto.getActors(), newFilm.getId()) : Collections.emptyList();

        return DtoMapper.fromFilmToDto(newFilm, genres, actors);
    }

    private void throwIfFilmNotValid(FilmDto filmDto) {
        if (filmDto.getId() == null && filmRepository.findByNameAndYear(filmDto.getName().trim(), filmDto.getYear()).isPresent()) {
            throw new BadRequestException("Фильм с таким названием и этого же года выпуска уже существует.");
        }
        if (!filmDto.getActors().isEmpty()) {
            var size = filmDto.getActors().stream()
                    .map(a -> a.getFirstName().trim().toLowerCase() + a.getSecondName().trim().toLowerCase() + a.getRole().toLowerCase())
                    .distinct()
                    .toList()
                    .size();

            if (size < filmDto.getActors().size()) {
                throw new BadRequestException("Вы ввели актёров с одинакомы именем, фамилией и ролью.");
            }
        }
    }
}
