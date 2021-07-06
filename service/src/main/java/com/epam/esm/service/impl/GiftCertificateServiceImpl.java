package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.GiftCertificateDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GiftCertificateServiceImpl {
    private final GiftCertificateDAOImpl giftCertificateDAO;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDAOImpl giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }


}
