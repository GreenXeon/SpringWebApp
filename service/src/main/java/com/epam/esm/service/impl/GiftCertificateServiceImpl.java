package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.*;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.util.ServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final Logger logger = LogManager.getLogger(GiftCertificateServiceImpl.class);

    private final GiftCertificateDAO giftCertificateDAO;

    private final TagService tagService;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, TagService tagService) {
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagService = tagService;
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate)
            throws GiftCertificateServiceException, TagAlreadyExistsException, GiftCertificateAlreadyExistsException {
        try {
            if (giftCertificateDAO.getCertificateByName(giftCertificate.getName()) != null){
                throw new GiftCertificateAlreadyExistsException("Certificate with name '" + giftCertificate.getName()
                + "' already exists");
            }
            giftCertificate.setCreateDate(ServiceUtils.getCurrentDateTime());
            giftCertificate.setLastUpdateDate(ServiceUtils.getCurrentDateTime());
            GiftCertificate newCertificate = giftCertificateDAO.create(giftCertificate);
            Set<Tag> tags = new HashSet<>(giftCertificate.getTags());
            for (Tag tag : tags){
                if (tagService.getTagByName(tag.getName()) == null){
                    tagService.create(tag);
                }
                tag.setId(tagService.getTagByName(tag.getName()).getId());
                tagService.saveCertificateTag(
                        newCertificate.getId(),
                        tagService.getTagByName(tag.getName()).getId());
            }
            newCertificate.setTags(tags);
            return newCertificate;
        } catch (DaoCreateException | TagServiceException e) {
            throw new GiftCertificateServiceException("Creating certificate '" +
                    giftCertificate.getName() + "' is failed", e);
        }
    }

    @Override
    public List<GiftCertificate> getAllCertificates() {
        List<GiftCertificate> giftCertificates =  giftCertificateDAO.getAllCertificates();
        giftCertificates.forEach(
                giftCertificate -> giftCertificate.setTags(
                      new HashSet<>(tagService.getTagsByCertificateId(giftCertificate.getId()))
                )
        );
        return giftCertificates;
    }

    @Override
    public GiftCertificate getCertificateById(Long id) throws GiftCertificateNotFoundException {
        GiftCertificate giftCertificate = giftCertificateDAO.getCertificateById(id);
        if (giftCertificate == null){
            throw new GiftCertificateNotFoundException("Certificate with id " + id + " is not found");
        }
        Set<Tag> tagsOfCertificate = new HashSet<>(tagService.getTagsByCertificateId(id));
        giftCertificate.setTags(tagsOfCertificate);
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
    public List<GiftCertificate> getAllCertificatesByQuery(Map<String, String> params) {
        List<GiftCertificate> giftCertificates = giftCertificateDAO.getAllCertificatesByQuery(params);
        giftCertificates.forEach(
                giftCertificate -> giftCertificate.setTags(
                        new HashSet<>(tagService.getTagsByCertificateId(giftCertificate.getId()))
                )
        );
        return giftCertificates;
    }

    @Override
    public GiftCertificate update(Long id, GiftCertificate changingCertificate)
            throws GiftCertificateServiceException, GiftCertificateNotFoundException, TagAlreadyExistsException,
                GiftCertificateAlreadyExistsException {
        try {
            if (!giftCertificateDAO.getCertificateById(id).getName().equalsIgnoreCase(changingCertificate.getName())
                && changingCertificate.getName() != null){
                if (giftCertificateDAO.getCertificateByName(changingCertificate.getName()) != null){
                    throw new GiftCertificateAlreadyExistsException("Certificate with name '" +
                            changingCertificate.getName() + "' already exists");
                }
            }
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

            Set<Tag> newTags = changingCertificate.getTags();
            if (newTags != null) {
                tagService.deleteCertificateTag(id);
                for (Tag tag : newTags) {
                    if (tagService.getTagByName(tag.getName()) == null){
                        tag = tagService.create(tag);
                    } else {
                        tag.setId(tagService.getTagByName(tag.getName()).getId());
                    }
                    tagService.saveCertificateTag(id, tag.getId());
                }
            }
            newCertificate.setTags(newTags);
            return giftCertificateDAO.update(id, newCertificate);
        } catch (DaoUpdateException | TagServiceException | DaoDeleteException | DaoCreateException e) {
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
