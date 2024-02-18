package ru.vladbakumenko.film_catalog_app.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vladbakumenko.film_catalog_app.dao.FilmRepository;
import ru.vladbakumenko.film_catalog_app.mapper.FilmMapper;
import ru.vladbakumenko.film_catalog_app.model.Film;

import java.util.List;
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
        return Optional.empty();
    }

    @Override
    public Film create(Film film) {
        return null;
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
