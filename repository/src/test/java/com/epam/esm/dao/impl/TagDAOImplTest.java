package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class TagDAOImplTest {

    private static TagDAO tagDAO;

    private final Tag tag = new Tag.Builder()
            .withName("Sample name")
            .build();

    @BeforeAll
    public static void setup(){
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScript("classpath:springDB.sql")
                .addScript("classpath:testdata.sql")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        tagDAO = new TagDAOImpl(jdbcTemplate);
    }

    @Test
    void getAllTags() {
        assertEquals(tagDAO.getAllTags().size(), 3);
    }

    @Test
    void getTagByName() {
        assertEquals(tagDAO.getTagByName("mjc").getName(), "mjc");
        assertNull(tagDAO.getTagByName("not existing name"));
    }

    @Test
    void delete() { // TODO: logging
        assertEquals(tagDAO.getAllTags().size(), 3);
        System.out.println(tagDAO.getAllTags());
        tagDAO.delete(1);
        assertEquals(tagDAO.getAllTags().size(), 2);
        System.out.println(tagDAO.getAllTags());
    }

    @Test
    void create() {
        int oldSize = tagDAO.getAllTags().size();
        tagDAO.create(tag);
        int newSize = tagDAO.getAllTags().size();
        assertEquals(newSize, ++oldSize);
    }
}