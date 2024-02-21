package ru.vladbakumenko.film_catalog_app.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vladbakumenko.film_catalog_app.dao.GenreRepository;
import ru.vladbakumenko.film_catalog_app.model.FilmAndGenre;
import ru.vladbakumenko.film_catalog_app.model.Genre;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Slf4j
public class GenreRepositoryImpl implements GenreRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Genre> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Genre create(Genre genre) {
        return null;
    }

    @Override
    public Genre update(Genre genre) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public List<FilmAndGenre> findFilmAndGenreDtoByFilmIds(List<Long> filmIds) {
        log.info(filmIds.toString());
        SqlParameterSource parameters = new MapSqlParameterSource("filmIds", filmIds);
        var sql = "select * from films_with_genres fwg, genres g where fwg.genre_fk = g.id and fwg.film_fk in (:filmIds)";
        return jdbcTemplate.query(sql, parameters, (rs, rowNum) ->
                new FilmAndGenre(rs.getLong("film_fk"),
                        rs.getInt("genre_fk"),
                        rs.getString("name")));
    }
}
