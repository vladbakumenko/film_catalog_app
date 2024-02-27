package ru.vladbakumenko.film_catalog_app.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vladbakumenko.film_catalog_app.dao.FilmRepository;
import ru.vladbakumenko.film_catalog_app.mapper.FilmMapper;
import ru.vladbakumenko.film_catalog_app.model.Film;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FilmRepositoryImpl implements FilmRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Film> findAll() {
        var sql = "select * from films";
        return jdbcTemplate.query(sql, new FilmMapper());
    }

    @Override
    public Optional<Film> findById(Long id) {
        var sql = "select * from films where id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new FilmMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Film> findByNameAndYear(String name, Integer year) {
        var sql = "select * from films where name ilike ? and year = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new FilmMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    @Override
    @Transactional
    public Film create(Film film) {
        String sqlFilm = "insert into films(name, year, description, rating) values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlFilm, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, film.getName());
            ps.setInt(2, film.getYear());
            ps.setString(3, film.getDescription());
            ps.setFloat(4, film.getRating());
            return ps;
        }, keyHolder);

        Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        film.setId(id);

        return film;
    }

    @Override
    public Film update(Film film) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Film> findByWordInTitle(String word) {
        return null;
    }

    @Override
    public List<Film> findBySecondNameOfActor(String secondName) {
        return null;
    }
}
