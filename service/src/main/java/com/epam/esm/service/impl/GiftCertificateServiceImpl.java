package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.impl.GiftCertificateDAOImpl;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.*;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.util.ServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final Logger logger = LogManager.getLogger(GiftCertificateServiceImpl.class);

    private final GiftCertificateDAO giftCertificateDAO;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) throws GiftCertificateServiceException {
        try {
            giftCertificate.setCreateDate(ServiceUtils.getCurrentDateTime());
            giftCertificate.setLastUpdateDate(ServiceUtils.getCurrentDateTime());
            return giftCertificateDAO.create(giftCertificate);
        } catch (DaoCreateException e) {
            throw new GiftCertificateServiceException("Creating certificate '" + giftCertificate.getName() + "' is failed", e);
        }
    }

    @Override
    public List<GiftCertificate> getAllCertificates() {
        return giftCertificateDAO.getAllCertificates();
    }

    @Override
    public GiftCertificate getCertificateById(Long id) throws GiftCertificateNotFoundException {
        GiftCertificate giftCertificate = giftCertificateDAO.getCertificateById(id);
        if (giftCertificate == null){
            throw new GiftCertificateNotFoundException("Certificate with id " + id + " is not found");
        }
        return giftCertificate;
    }

    @Override
    public GiftCertificate getCertificateByTagName(String tagName) throws GiftCertificateNotFoundException {
        GiftCertificate giftCertificate = giftCertificateDAO.getCertificateByTagName(tagName);
        if (giftCertificate == null){
            throw new GiftCertificateNotFoundException("Certificate with tag name '" + tagName + "' is not found");
        }
        return giftCertificate;
    }

    @Override
    public GiftCertificate update(Long id, GiftCertificate changingCertificate)
            throws GiftCertificateServiceException, GiftCertificateNotFoundException {
        try {
            GiftCertificate newCertificate = new GiftCertificate();
            GiftCertificate oldCertificate = getCertificateById(id);
            newCertificate.setId(oldCertificate.getId());
            newCertificate.setName(changingCertificate.getName() == null ? oldCertificate.getName()
                    : changingCertificate.getName());
            newCertificate.setDescription(changingCertificate.getDescription() == null ? oldCertificate.getDescription()
                    : changingCertificate.getDescription());
            newCertificate.setPrice(changingCertificate.getPrice() == null ? oldCertificate.getPrice()
                    : changingCertificate.getPrice());
            newCertificate.setDuration(changingCertificate.getDuration() == null ? oldCertificate.getDuration()
                    : changingCertificate.getDuration());
            newCertificate.setCreateDate(changingCertificate.getCreateDate() == null ? oldCertificate.getCreateDate()
                    : changingCertificate.getCreateDate());
            newCertificate.setLastUpdateDate(ServiceUtils.getCurrentDateTime());
            return giftCertificateDAO.update(id, newCertificate);
        } catch (DaoUpdateException e) {
            throw new GiftCertificateServiceException("Cannot update certificate with id " + id, e);
        }
    }

    @Override
    public void delete(Long id) throws GiftCertificateServiceException, GiftCertificateNotFoundException {
        try {
            this.getCertificateById(id);
            giftCertificateDAO.delete(id);
        } catch (DaoDeleteException e) {
            throw new GiftCertificateServiceException("Cannot delete certificate with id " + id, e);
        }
    }
}
