package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateDAOImplTest {
    private final Logger logger = LogManager.getLogger(GiftCertificateDAOImplTest.class);

    private static GiftCertificateDAO giftCertificateDAO;

    private final GiftCertificate giftCertificate = new GiftCertificate.Builder()
            .withName("sample")
            .withDescription("description")
            .withCreateDate(LocalDateTime.now())
            .withLastUpdateDate(LocalDateTime.now())
            .withDuration(5)
            .withPrice(BigDecimal.TEN)
            .build();

    @BeforeAll
    public static void setup(){
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScript("classpath:springDB.sql")
                .addScript("classpath:testdata.sql")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        giftCertificateDAO = new GiftCertificateDAOImpl(jdbcTemplate);
    }

    @Test
    void create() {
        int oldSize = giftCertificateDAO.getAllCertificates().size();
        assertNotNull(giftCertificateDAO.create(giftCertificate));
        int newSize = giftCertificateDAO.getAllCertificates().size();
        assertEquals(newSize, oldSize + 1);
    }

    @Test
    void getAllCertificates() {
        assertTrue(giftCertificateDAO.getAllCertificates().size() > 1);
    }

    @Test
    void getCertificateByTagName() {
        assertNotNull(giftCertificateDAO.getCertificateByTagName("mjc"));
        assertNull(giftCertificateDAO.getCertificateByTagName("notexists"));
    }

    @Test
    void update() {
        long id = 1L;
        assertNotNull(giftCertificateDAO.update(id, giftCertificate));
    }

    @Test
    void delete() {
        int oldSize = giftCertificateDAO.getAllCertificates().size();
        long id = 2L;
        giftCertificateDAO.delete(id);
        int newSize = giftCertificateDAO.getAllCertificates().size();
        assertEquals(newSize, oldSize - 1);

    }

    @Test
    void getCertificateById() {
        long id = 1L;
        GiftCertificate giftCertificate = giftCertificateDAO.getCertificateById(id);
        assertNotNull(giftCertificate);
        assertEquals(giftCertificate.getName(), "first");
    }
}