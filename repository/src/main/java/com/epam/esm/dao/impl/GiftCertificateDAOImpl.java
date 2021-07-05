package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftCertificateDAOImpl implements GiftCertificateDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) {
        return null;
    }

    @Override
    public List<GiftCertificate> getAllCertificates() {
        return null;
    }

    @Override
    public GiftCertificate getCertificateByTagName(String tagName) {
        return null;
    }

    @Override
    public GiftCertificate update(int id, GiftCertificate newCertificate) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
