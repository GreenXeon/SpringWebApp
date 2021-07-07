package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        assertNotEquals(giftCertificateDAO.create(giftCertificate), null);
        int newSize = giftCertificateDAO.getAllCertificates().size();
        assertEquals(newSize, oldSize + 1);
    }

    @Test
    void getAllCertificates() {
        assertEquals(giftCertificateDAO.getAllCertificates().size(), 3);
    }

    @Test
    void getCertificateByTagName() {
        assertEquals(giftCertificateDAO.getCertificateByTagName("mjc").getId(), 1L);
        assertNull(giftCertificateDAO.getCertificateByTagName("notexists"));
    }

    @Test
    void update() {
        long id = 1L;
        assertNotNull(giftCertificateDAO.update(id, giftCertificate));
    }

    @Test
    void delete() {
    }

    @Test
    void getCertificateById() {
        assertNotNull(giftCertificateDAO.getCertificateById(1L));
        assertEquals(giftCertificateDAO.getCertificateById(1L).getName(), "first");
    }
}