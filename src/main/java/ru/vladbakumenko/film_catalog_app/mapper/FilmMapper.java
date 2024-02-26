package ru.vladbakumenko.film_catalog_app.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.vladbakumenko.film_catalog_app.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper implements RowMapper<Film> {

    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Film.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .year(rs.getInt("year"))
                .description(rs.getString("description"))
                .rating(rs.getFloat("rating"))
                .build();
    }
}
