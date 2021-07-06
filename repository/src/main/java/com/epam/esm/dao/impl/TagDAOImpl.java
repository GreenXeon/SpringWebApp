package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagDAOImpl implements TagDAO {
    private static final String GET_ALL_TAGS = "SELECT * FROM springdb.tag";
    private static final String GET_TAG_BY_NAME = "SELECT * FROM springdb.tag WHERE name = ?";
    private static final String SAVE_TAG = "INSERT INTO springdb.tag (name) VALUES (?)";
    private static final String DELETE_TAG_BY_ID = "DELETE FROM springdb.tag WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag create(Tag tag) {
        return jdbcTemplate.update(SAVE_TAG, tag.getName()) == 1 ? tag : null;
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(GET_ALL_TAGS, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag getTagByName(String name) {
        return jdbcTemplate.query(GET_TAG_BY_NAME, new BeanPropertyRowMapper<>(Tag.class), name)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_TAG_BY_ID, id);
    }
}
