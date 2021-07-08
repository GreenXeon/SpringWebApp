package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.GiftCertificateDAOImpl;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDAOImpl giftCertificateDAO;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDAOImpl giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }


    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) {
        return null;
    }

    @Override
    public List<GiftCertificate> getAllCertificates() {
        return null;
    }

    @Override
    public GiftCertificate getCertificateById(Long id) {
        return null;
    }

    @Override
    public GiftCertificate getCertificateByTagName(String tagName) {
        return null;
    }

    @Override
    public GiftCertificate update(Long id, GiftCertificate newCertificate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
