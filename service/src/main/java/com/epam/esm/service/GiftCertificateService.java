package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.GiftCertificateServiceException;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificate create(GiftCertificate giftCertificate) throws GiftCertificateServiceException;

    List<GiftCertificate> getAllCertificates();

    GiftCertificate getCertificateById(Long id) throws GiftCertificateServiceException;

    GiftCertificate getCertificateByTagName(String tagName) throws GiftCertificateServiceException;

    GiftCertificate update(Long id, GiftCertificate newCertificate) throws GiftCertificateServiceException;

    void delete(Long id) throws GiftCertificateServiceException;
}
