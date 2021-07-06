package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Tag.Builder()
                .withId(resultSet.getLong("id"))
                .withName(resultSet.getString("name"))
                .build();
    }
}
