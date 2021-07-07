package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateDAO {
    GiftCertificate create(GiftCertificate giftCertificate);

    List<GiftCertificate> getAllCertificates();

    GiftCertificate getCertificateById(Long id);

    GiftCertificate getCertificateByTagName(String tagName);

    GiftCertificate update(Long id, GiftCertificate newCertificate);

    void delete(Long id);
}
