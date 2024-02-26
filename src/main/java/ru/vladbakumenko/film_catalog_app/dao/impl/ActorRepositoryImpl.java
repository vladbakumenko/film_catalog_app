package ru.vladbakumenko.film_catalog_app.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vladbakumenko.film_catalog_app.dao.ActorRepository;
import ru.vladbakumenko.film_catalog_app.model.Actor;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ActorRepositoryImpl implements ActorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Actor> findActorsByFilmIds(List<Long> filmIds) {
        SqlParameterSource parameters = new MapSqlParameterSource("filmIds", filmIds);
        var sql = "select * from actors_roles ar, actors a where ar.actor_fk = a.id and ar.film_fk in (:filmIds)";
        return jdbcTemplate.query(sql, parameters, (rs, rowNum) ->
                new Actor(rs.getLong("film_fk"),
                        rs.getLong("actor_fk"),
                        rs.getString("role"),
                        rs.getString("first_name"),
                        rs.getString("second_name")));
    }
}
