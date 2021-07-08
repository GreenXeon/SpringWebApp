package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    void getAllTags() {
        assertEquals(tagDAO.getAllTags().size(), 3);
    }

    @Test
    @Order(3)
    void getTagByName() {
        assertEquals(tagDAO.getTagByName("mjc").getName(), "mjc");
        assertNull(tagDAO.getTagByName("not existing name"));
    }

    @Test
    @Order(2)
    void delete() {
        assertEquals(tagDAO.getAllTags().size(), 3);
        tagDAO.delete(1L);
        assertEquals(tagDAO.getAllTags().size(), 2);
    }

    @Test
    @Order(4)
    void create() {
        int oldSize = tagDAO.getAllTags().size();
        tagDAO.create(tag);
        int newSize = tagDAO.getAllTags().size();
        assertEquals(newSize, ++oldSize);
    }
}