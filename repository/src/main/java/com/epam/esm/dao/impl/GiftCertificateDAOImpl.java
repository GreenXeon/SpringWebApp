package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftCertificateDAOImpl implements GiftCertificateDAO {
    private static final String GET_ALL_CERTIFICATES = "SELECT * FROM springdb.gift_certificate";
    private static final String GET_CERTIFICATE_BY_ID = "SELECT * FROM springdb.gift_certificate WHERE id = ?";
    private static final String SAVE_CERTIFICATE = "INSERT INTO springdb.gift_certificate" +
            " (name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CERTIFICATE_BY_ID = "DELETE FROM springdb.gift_certificate WHERE id = ?";
    private static final String GET_CERTIFICATE_WITH_TAG = "SELECT * FROM springdb.gift_certificate WHERE id = " +
            "(SELECT gift_certificate_id FROM springdb.gift_certificate_has_tag WHERE tag_id = " +
            "(SELECT id FROM springdb.tag WHERE name = ?))";
    private static final String UPDATE_CERTIFICATE = "UPDATE springdb.gift_certificate SET name = ?," +
            "description = ?, price = ?, duration = ?, create_date = ?, last_update_date = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) throws DaoCreateException {
        if (jdbcTemplate.update(SAVE_CERTIFICATE,
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                giftCertificate.getCreateDate(),
                giftCertificate.getLastUpdateDate()) == 0){
            throw new DaoCreateException("Cannot create certificate with name " + giftCertificate.getName());
        }
            return giftCertificate;
    }

    @Override
    public List<GiftCertificate> getAllCertificates() {
        return jdbcTemplate.query(GET_ALL_CERTIFICATES, new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public GiftCertificate getCertificateById(Long id) {
        GiftCertificate giftCertificate = jdbcTemplate.query(GET_CERTIFICATE_BY_ID,
                new BeanPropertyRowMapper<>(GiftCertificate.class), id)
                .stream()
                .findAny()
                .orElse(null);
        return giftCertificate;
    }

    @Override
    public GiftCertificate getCertificateByTagName(String tagName) {
        GiftCertificate giftCertificate = jdbcTemplate.query(GET_CERTIFICATE_WITH_TAG,
                new BeanPropertyRowMapper<>(GiftCertificate.class), tagName)
                .stream()
                .findAny()
                .orElse(null);
        return giftCertificate;
    }

    @Override
    public GiftCertificate update(Long id, GiftCertificate newCertificate) throws DaoUpdateException {
        if (jdbcTemplate.update(UPDATE_CERTIFICATE,
                newCertificate.getName(),
                newCertificate.getDescription(),
                newCertificate.getPrice(),
                newCertificate.getDuration(),
                newCertificate.getCreateDate(),
                newCertificate.getLastUpdateDate(),
                id) == 0){
            throw new DaoUpdateException("Cannot update certificate with id " + id);
        }
            return newCertificate;
    }

    @Override
    public void delete(Long id) throws DaoDeleteException {
        if (jdbcTemplate.update(DELETE_CERTIFICATE_BY_ID, id) == 0){
            throw new DaoDeleteException("Cannot delete certificate with id " + id);
        }
    }
}
