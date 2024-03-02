package ru.vladbakumenko.film_catalog_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vladbakumenko.film_catalog_app.dao.impl.GenreRepositoryImpl;
import ru.vladbakumenko.film_catalog_app.dto.GenreDto;
import ru.vladbakumenko.film_catalog_app.mapper.DtoMapper;
import ru.vladbakumenko.film_catalog_app.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepositoryImpl genreRepository;

    public List<GenreDto> create(List<GenreDto> genreDtos, Long filmId) {
        List<Genre> genres = genreDtos.stream().map(dto -> DtoMapper.fromDtoToGenre(dto, filmId)).toList();
        return genreRepository.create(genres).stream().map(DtoMapper::fromGenreToDto).toList();
    }

    public Map<Long, List<Genre>> findGenresByFilmIds(List<Long> filmIds) {
        Map<Long, List<Genre>> res = new ConcurrentHashMap<>();

        List<Genre> filmsAndGenres = genreRepository.findGenresByFilmIds(filmIds);
        if (!filmsAndGenres.isEmpty()) {
            filmsAndGenres.forEach(fagd -> {
                        List<Genre> genres = res.getOrDefault(fagd.getFilmId(), new ArrayList<>());
                        genres.add(fagd);
                        res.put(fagd.getFilmId(), genres);
                    }
            );
        }
        return res;
    }
}
