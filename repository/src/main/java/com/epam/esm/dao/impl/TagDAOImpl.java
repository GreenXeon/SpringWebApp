package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.TagMapper;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagDAOImpl implements TagDAO {
    private static final String GET_ALL_TAGS = "SELECT * FROM springdb.tag";
    private static final String GET_TAG_BY_NAME = "SELECT * FROM springdb.tag WHERE name = :name";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag create(Tag tag) {
        return null;
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(GET_ALL_TAGS, new TagMapper());
    }

    @Override
    public Tag getTagByName(String name) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
