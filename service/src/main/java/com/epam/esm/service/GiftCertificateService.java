package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.*;

import java.util.List;

public interface GiftCertificateService {
    GiftCertificate create(GiftCertificate giftCertificate) throws GiftCertificateServiceException, TagAlreadyExistsException, GiftCertificateAlreadyExistsException;

    List<GiftCertificate> getAllCertificates();

    GiftCertificate getCertificateById(Long id) throws GiftCertificateServiceException, GiftCertificateNotFoundException;

    GiftCertificate getCertificateByTagName(String tagName) throws GiftCertificateServiceException, GiftCertificateNotFoundException;

    GiftCertificate update(Long id, GiftCertificate newCertificate) throws GiftCertificateServiceException, GiftCertificateNotFoundException, TagAlreadyExistsException, GiftCertificateAlreadyExistsException;

    void delete(Long id) throws GiftCertificateServiceException, GiftCertificateNotFoundException;
}
