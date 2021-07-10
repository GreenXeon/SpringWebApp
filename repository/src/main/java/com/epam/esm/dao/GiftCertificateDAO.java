package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.*;

import java.util.List;

public interface GiftCertificateDAO {
    GiftCertificate create(GiftCertificate giftCertificate) throws DaoCreateException;

    List<GiftCertificate> getAllCertificates();

    GiftCertificate getCertificateById(Long id);

    GiftCertificate getCertificateByTagName(String tagName);

    GiftCertificate update(Long id, GiftCertificate newCertificate) throws DaoUpdateException;

    void delete(Long id) throws DaoDeleteException;
}
