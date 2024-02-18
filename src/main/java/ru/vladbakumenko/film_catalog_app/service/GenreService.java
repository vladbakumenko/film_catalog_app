package ru.vladbakumenko.film_catalog_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vladbakumenko.film_catalog_app.dao.impl.GenreRepositoryImpl;
import ru.vladbakumenko.film_catalog_app.dto.FilmAndGenreDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepositoryImpl genreRepository;

    public Map<Long, List<FilmAndGenreDto>> findFilmAndGenreDtoByFilmIds(List<Long> filmIds) {
        Map<Long, List<FilmAndGenreDto>> res = new HashMap<>();

        List<FilmAndGenreDto> filmAndGenreDtos = genreRepository.findFilmAndGenreDtoByFilmIds(filmIds);
        if (!filmAndGenreDtos.isEmpty()) {
            filmAndGenreDtos.forEach(fagd ->
                res.getOrDefault(fagd.getFilmId(), new ArrayList<>()).add(fagd));
        }
        return res;
    }
}
