package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoCreateException;
import com.epam.esm.exception.DaoDeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagDAOImpl implements TagDAO {
    private static final String GET_ALL_TAGS = "SELECT * FROM springdb.tag";
    private static final String GET_TAG_BY_ID = "SELECT * FROM springdb.tag WHERE id = ?";
    private static final String GET_TAG_BY_NAME = "SELECT * FROM springdb.tag WHERE name = ?";
    private static final String SAVE_TAG = "INSERT INTO springdb.tag (name) VALUES (?)";
    private static final String DELETE_TAG_BY_ID = "DELETE FROM springdb.tag WHERE id = ?";
    private static final String GET_TAGS_BY_CERTIFICATE_ID = "SELECT t.id, t.name FROM springdb.tag t " +
            "JOIN gift_certificate_has_tag ct on t.id = ct.tag_id " +
            "JOIN gift_certificate gc on gc.id = ct.gift_certificate_id " +
            "WHERE gc.id = ?";
    private static final String SAVE_CERTIFICATE_TAG = "INSERT INTO springdb.gift_certificate_has_tag " +
            "(gift_certificate_id, tag_id) VALUES (?, ?);";
    private static final String DELETE_CERTIFICATE_TAG = "DELETE FROM springdb.gift_certificate_has_tag " +
            "WHERE gift_certificate_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag create(Tag tag) throws DaoCreateException {
        /*if (jdbcTemplate.update(SAVE_TAG, tag.getName()) == 0){
            throw new DaoCreateException("Cannot save tag with name " + tag.getName());
        } else {
            return tag;
        }*/
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("tag")
                .usingGeneratedKeyColumns("id");
        Number id = simpleJdbcInsert.executeAndReturnKey(
                new BeanPropertySqlParameterSource(tag));
        tag.setId(id.longValue());
        return tag;
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(GET_ALL_TAGS, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag getTagById(Long id){
        Tag tag = jdbcTemplate.query(GET_TAG_BY_ID, new BeanPropertyRowMapper<>(Tag.class), id)
                .stream()
                .findAny()
                .orElse(null);
        return tag;
    }

    @Override
    public Tag getTagByName(String name) {
        Tag tag = jdbcTemplate.query(GET_TAG_BY_NAME, new BeanPropertyRowMapper<>(Tag.class), name)
                .stream()
                .findAny()
                .orElse(null);
        return tag;
    }

    @Override
    public void saveCertificateTag(Long certificateId, Long tagId) throws DaoCreateException {
        if (jdbcTemplate.update(SAVE_CERTIFICATE_TAG, certificateId, tagId) == 0){
            throw new DaoCreateException("Cannot connect certificate (id = " + certificateId +") with " +
                    "tag (id = " + tagId + ")");
        }
    }

    @Override
    public void deleteCertificateTag(Long certificateId) throws DaoDeleteException {
        if (jdbcTemplate.update(DELETE_CERTIFICATE_TAG, certificateId) == 0){
            throw new DaoDeleteException("Cannot delete tags for certificate (id = " + certificateId + ")");
        }
    }


    @Override
    public void delete(Long id) throws DaoDeleteException {
        if (jdbcTemplate.update(DELETE_TAG_BY_ID, id) == 0){
            throw new DaoDeleteException("Cannot delete tag with id " + id);
        }
    }

    @Override
    public List<Tag> getTagsByCertificateId(Long id) {
        return jdbcTemplate.query(GET_TAGS_BY_CERTIFICATE_ID, new BeanPropertyRowMapper<>(Tag.class), id);
    }
}
