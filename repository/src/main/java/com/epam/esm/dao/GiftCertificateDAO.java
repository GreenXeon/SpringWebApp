package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.*;

import java.util.List;
import java.util.Map;

public interface GiftCertificateDAO {
    GiftCertificate create(GiftCertificate giftCertificate) throws DaoCreateException;

    List<GiftCertificate> getAllCertificates();

    GiftCertificate getCertificateById(Long id);

    GiftCertificate getCertificateByName(String name);

    GiftCertificate getCertificateByTagName(String tagName);

    List<GiftCertificate> getAllCertificatesByQuery(Map<String, String> params);

    GiftCertificate update(Long id, GiftCertificate newCertificate) throws DaoUpdateException;

    void delete(Long id) throws DaoDeleteException;
}
