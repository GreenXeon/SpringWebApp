package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagDAOImplTest {

    private static TagDAO tagDAO;

    private final Tag tag = new Tag.Builder()
            .withId(1L)
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
}