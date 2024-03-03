package ru.vladbakumenko.film_catalog_app.dao;

import ru.vladbakumenko.film_catalog_app.model.Actor;

import java.util.List;

public interface ActorRepository extends BaseRepository<List<Actor>> {

    List<Actor> findActorsByFilmIds(List<Long> filmIds);
}
