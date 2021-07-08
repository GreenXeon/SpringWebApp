package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.DaoCreateException;
import com.epam.esm.exception.DaoDeleteException;
import com.epam.esm.exception.DaoUpdateException;
import com.epam.esm.exception.GiftCertificateNotFoundException;

import java.util.List;

public interface GiftCertificateDAO {
    GiftCertificate create(GiftCertificate giftCertificate) throws DaoCreateException;

    List<GiftCertificate> getAllCertificates();

    GiftCertificate getCertificateById(Long id) throws GiftCertificateNotFoundException;

    GiftCertificate getCertificateByTagName(String tagName) throws GiftCertificateNotFoundException;

    GiftCertificate update(Long id, GiftCertificate newCertificate) throws DaoUpdateException;

    void delete(Long id) throws DaoDeleteException;
}
