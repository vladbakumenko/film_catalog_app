package ru.vladbakumenko.film_catalog_app.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import ru.vladbakumenko.film_catalog_app.dao.GenreRepository;
import ru.vladbakumenko.film_catalog_app.model.Genre;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Genre> create(List<Genre> genres) {
        var sql = "insert into films_with_genres(genre_fk, film_fk) values (:genreId, :filmId)";

        jdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(genres));

        return genres;
    }

    @Override
    public List<Genre> update(List<Genre> genres) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<List<Genre>> findById(Long id) {
        return Optional.empty();
    }
}
