package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.GiftCertificateServiceException;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificate create(GiftCertificate giftCertificate) throws GiftCertificateServiceException;

    List<GiftCertificate> getAllCertificates();

    GiftCertificate getCertificateById(Long id) throws GiftCertificateServiceException, GiftCertificateNotFoundException;

    GiftCertificate getCertificateByTagName(String tagName) throws GiftCertificateServiceException, GiftCertificateNotFoundException;

    GiftCertificate update(Long id, GiftCertificate newCertificate) throws GiftCertificateServiceException, GiftCertificateNotFoundException;

    void delete(Long id) throws GiftCertificateServiceException, GiftCertificateNotFoundException;
}
