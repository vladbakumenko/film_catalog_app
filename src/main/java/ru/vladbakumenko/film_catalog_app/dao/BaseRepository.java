package ru.vladbakumenko.film_catalog_app.dao;

import ru.vladbakumenko.film_catalog_app.model.Film;

import java.util.Optional;

interface BaseRepository<T> {

    Optional<T> findById(Long id);

    T create(T t);

    T update(T t);

    void deleteById(Long id);
}
