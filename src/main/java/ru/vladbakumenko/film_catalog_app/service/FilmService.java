package ru.vladbakumenko.film_catalog_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vladbakumenko.film_catalog_app.dao.impl.FilmRepositoryImpl;
import ru.vladbakumenko.film_catalog_app.model.FilmAndGenre;
import ru.vladbakumenko.film_catalog_app.dto.FilmDto;
import ru.vladbakumenko.film_catalog_app.model.Film;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final FilmRepositoryImpl filmRepository;
    private final GenreService genreService;

    public List<FilmDto> getAll() {
        List<Film> films = filmRepository.findAll();
        Map<Long, List<FilmAndGenre>> filmsAndGenres =
                genreService.findFilmAndGenreDtoByFilmIds(films.stream().map(Film::getId).toList());

        return films.stream().map(f -> new FilmDto(
                f.getId(),
                f.getName(),
                f.getYear(),
                f.getDescription(),
                f.getRating(),
                filmsAndGenres.get(f.getId()),
                null
        )).toList();
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
