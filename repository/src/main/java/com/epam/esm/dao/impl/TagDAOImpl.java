package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoCreateException;
import com.epam.esm.exception.DaoDeleteException;
import com.epam.esm.exception.TagNotFoundException;
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
    public Tag create(Tag tag) throws DaoCreateException {
        if (jdbcTemplate.update(SAVE_TAG, tag.getName()) == 0){
            throw new DaoCreateException("Cannot save tag with name " + tag.getName());
        } else {
            return tag;
        }
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(GET_ALL_TAGS, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag getTagByName(String name) throws TagNotFoundException {
        Tag tag = jdbcTemplate.query(GET_TAG_BY_NAME, new BeanPropertyRowMapper<>(Tag.class), name)
                .stream()
                .findAny()
                .orElse(null);
        if (tag == null){
            throw new TagNotFoundException("Tag with name " + name + " is not found");
        }
        return tag;
    }

    @Override
    public void delete(Long id) throws DaoDeleteException {
        if (jdbcTemplate.update(DELETE_TAG_BY_ID, id) == 0){
            throw new DaoDeleteException("Cannot delete tag with id " + id);
        }
    }
}
