package ru.vladbakumenko.film_catalog_app.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vladbakumenko.film_catalog_app.dao.GenreRepository;
import ru.vladbakumenko.film_catalog_app.model.Genre;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class GenreRepositoryImpl implements GenreRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> findGenresByFilmIds(List<Long> filmIds) {
        SqlParameterSource parameters = new MapSqlParameterSource("filmIds", filmIds);
        var sql = "select * from films_with_genres fwg, genres g where fwg.genre_fk = g.id and fwg.film_fk in (:filmIds)";
        return jdbcTemplate.query(sql, parameters, (rs, rowNum) ->
                new Genre(rs.getLong("film_fk"),
                        rs.getInt("genre_fk"),
                        rs.getString("name")));
    }
}
