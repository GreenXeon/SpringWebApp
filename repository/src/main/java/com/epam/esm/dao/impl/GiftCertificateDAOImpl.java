package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GiftCertificateDAOImpl implements GiftCertificateDAO {
    private static final String GET_ALL_CERTIFICATES = "SELECT * FROM springdb.gift_certificate";
    private static final String GET_CERTIFICATE_BY_ID = "SELECT * FROM springdb.gift_certificate WHERE id = ?";
    private static final String GET_CERTIFICATE_BY_NAME = "SELECT * FROM springdb.gift_certificate WHERE name = ?";
    private static final String SAVE_CERTIFICATE = "INSERT INTO springdb.gift_certificate" +
            " (name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CERTIFICATE_BY_ID = "DELETE FROM springdb.gift_certificate WHERE id = ?";
    private static final String GET_CERTIFICATE_WITH_TAG = "SELECT * FROM springdb.gift_certificate WHERE id = " +
            "(SELECT gift_certificate_id FROM springdb.gift_certificate_has_tag WHERE tag_id = " +
            "(SELECT id FROM springdb.tag WHERE name = ?))";
    private static final String UPDATE_CERTIFICATE = "UPDATE springdb.gift_certificate SET name = ?," +
            "description = ?, price = ?, duration = ?, create_date = ?, last_update_date = ? WHERE id = ?";
    private static final String GET_ALL_CERTIFICATES_QUERY = "SELECT gc.id,gc.name,gc.description, gc.price," +
            " gc.duration, gc.create_date, gc.last_update_date" +
            "    FROM springdb.gift_certificate gc" +
            "        JOIN springdb.gift_certificate_has_tag ct on gc.id = ct.gift_certificate_id" +
            "        JOIN springdb.tag t on t.id = ct.tag_id";

    private final Logger logger = LogManager.getLogger(GiftCertificateDAOImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) throws DaoCreateException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("gift_certificate")
                .usingGeneratedKeyColumns("id");
        Number id = simpleJdbcInsert.executeAndReturnKey(
                new BeanPropertySqlParameterSource(giftCertificate));
        giftCertificate.setId(id.longValue());
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
    public GiftCertificate getCertificateByName(String name) {
        GiftCertificate giftCertificate = jdbcTemplate.query(GET_CERTIFICATE_BY_NAME,
                new BeanPropertyRowMapper<>(GiftCertificate.class), name)
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
    public List<GiftCertificate> getAllCertificatesByQuery(Map<String, String> params) {
        StringBuilder sql = new StringBuilder(GET_ALL_CERTIFICATES_QUERY);
        if (params.containsKey("name")) {
            sql.append(" WHERE t.name = '")
                    .append(params.get("name")).append("'")
                    .append(" OR gc.name like '%")
                    .append(params.get("name")).append("%'")
                    .append(" OR gc.description like '%")
                    .append(params.get("name")).append("%'");
        }
        sql.append(" GROUP BY gc.id ORDER BY gc.name ");
        if (params.containsKey("order")) {
            sql.append(" ").append(params.get("order"));
        }
        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(GiftCertificate.class));
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
