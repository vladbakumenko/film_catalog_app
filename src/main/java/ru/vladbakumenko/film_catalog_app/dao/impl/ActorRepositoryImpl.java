package ru.vladbakumenko.film_catalog_app.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vladbakumenko.film_catalog_app.dao.ActorRepository;
import ru.vladbakumenko.film_catalog_app.model.Actor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Optional<List<Actor>> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Actor> create(List<Actor> actors) {
        var sql1 = "insert into actors_roles(actor_fk, film_fk, role) values (:actorId, :filmId, :role)";
        var sql2 = "insert into actors(first_name, second_name) values (:firstName, :secondName)";

        Map<FirstAndSecondName, Actor> maybeNewActors = actors.stream()
                .collect(Collectors.toMap(a -> new FirstAndSecondName(a.getFirstName(), a.getSecondName()), a -> a));

        var alreadyExistsActors = getActorsByFirstAndSecondName(maybeNewActors.keySet());

        if (!alreadyExistsActors.isEmpty()) {
            actors = maybeNewActors.entrySet().stream()
                    .filter(e -> {
                        var existsActor = alreadyExistsActors.get(e.getKey());
                        if (existsActor != null) {
                            existsActor.setFilmId(e.getValue().getFilmId());
                            existsActor.setRole(e.getValue().getRole());
                            return false;
                        } else {
                            return true;
                        }
                    })
                    .map(Map.Entry::getValue)
                    .toList();

            jdbcTemplate.batchUpdate(sql1, SqlParameterSourceUtils.createBatch(alreadyExistsActors.values()));
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.batchUpdate(sql2, SqlParameterSourceUtils.createBatch(actors), keyHolder);

        Map<FirstAndSecondName, Long> keyMap = keyHolder.getKeyList().stream()
                .collect(Collectors.toMap(m -> new FirstAndSecondName((String) m.get("first_name"),
                        (String) m.get("second_name")), m -> (Long) m.get("id")));

        maybeNewActors.forEach((key, value) -> value.setActorId(keyMap.get(key)));

        jdbcTemplate.batchUpdate(sql1, SqlParameterSourceUtils.createBatch(actors));

        Collection<Actor> res = alreadyExistsActors.values();
        res.addAll(actors);
        return res.stream().toList();
    }

    @Override
    public List<Actor> update(List<Actor> actors) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @SneakyThrows
    public Map<FirstAndSecondName, Actor> getActorsByFirstAndSecondName(Set<FirstAndSecondName> names) {

        Map<FirstAndSecondName, Actor> res = new HashMap<>();

        var sql = "select * from actors where first_name ilike ? and second_name ilike ?";

        for (FirstAndSecondName name : names) {
            jdbcTemplate.getJdbcTemplate().query(sql, rs -> {
                var firstName = rs.getString("first_name");
                var secondName = rs.getString("second_name");
                Actor actor = new Actor(null,
                        rs.getLong("id"),
                        null,
                        firstName,
                        secondName
                );
                res.put(new FirstAndSecondName(firstName, secondName), actor);
            }, name.firstName, name.secondName);
        }

        return res;
    }

    record FirstAndSecondName(String firstName, String secondName) {
    }
}
