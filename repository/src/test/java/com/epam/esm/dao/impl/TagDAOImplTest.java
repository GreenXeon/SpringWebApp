package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoCreateException;
import com.epam.esm.exception.DaoDeleteException;
import com.epam.esm.exception.TagNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
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
    void getTagByName() throws TagNotFoundException {
        assertEquals(tagDAO.getTagByName("mjc").getName(), "mjc");
        assertThrows(TagNotFoundException.class, () -> tagDAO.getTagByName("not existing name"));
        assertDoesNotThrow(() -> tagDAO.getTagByName("hello"));
    }

    @Test
    @Order(2)
    void delete() throws DaoDeleteException {
        assertEquals(tagDAO.getAllTags().size(), 3);
        assertDoesNotThrow(() -> tagDAO.delete(1L));
        assertEquals(tagDAO.getAllTags().size(), 2);
    }

    @Test
    @Order(4)
    void create() {
        int oldSize = tagDAO.getAllTags().size();
        assertDoesNotThrow(() -> tagDAO.create(tag));
        int newSize = tagDAO.getAllTags().size();
        assertEquals(newSize, ++oldSize);
    }
}