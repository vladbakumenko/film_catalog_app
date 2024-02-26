package ru.vladbakumenko.film_catalog_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vladbakumenko.film_catalog_app.dao.impl.ActorRepositoryImpl;
import ru.vladbakumenko.film_catalog_app.dto.ActorDto;
import ru.vladbakumenko.film_catalog_app.model.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ActorService {

    private final ActorRepositoryImpl actorRepository;

    public Map<Long, List<Actor>> findActorsByFilmIds(List<Long> filmIds) {
        Map<Long, List<Actor>> res = new HashMap<>();

        List<Actor> filmsAndActors = actorRepository.findActorsByFilmIds(filmIds);

        if (!filmsAndActors.isEmpty()) {
            filmsAndActors.forEach(faa -> {
                var actors = res.getOrDefault(faa.getFilmId(), new ArrayList<>());
                actors.add(faa);
                res.put(faa.getFilmId(), actors);
            });
        }

        return res;
    }
}
