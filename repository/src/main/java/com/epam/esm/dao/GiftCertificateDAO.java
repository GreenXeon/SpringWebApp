package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateDAO {
    GiftCertificate create(GiftCertificate giftCertificate);

    List<GiftCertificate> getAllCertificates();

    GiftCertificate getCertificateByTagName(String tagName);

    GiftCertificate update(int id, GiftCertificate newCertificate);

    void delete(int id);
}
