package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.*;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagDAO tagDAO;

    @Autowired
    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public Tag create(Tag tag) throws TagServiceException, TagAlreadyExistsException {
        try {
            if (getTagByName(tag.getName()) != null){
                throw new TagAlreadyExistsException("Tag with name '" + tag.getName() + "' already exists");
            }
            return tagDAO.create(tag);
        } catch (DaoCreateException e) {
            throw new TagServiceException("Creating tag " + tag.getName() + " service error", e);
        }
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDAO.getAllTags();
    }

    @Override
    public Tag getTagById(Long id) throws TagNotFoundException {
        Tag tag = tagDAO.getTagById(id);
        if (tag == null){
            throw new TagNotFoundException("Tag with id " + id + " is not found");
        }
        return tag;
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDAO.getTagByName(name);
    }

    @Override
    public void saveCertificateTag(Long certificateId, Long tagId) throws DaoCreateException {
        tagDAO.saveCertificateTag(certificateId, tagId);
    }

    @Override
    public void deleteCertificateTag(Long certificateId) throws DaoDeleteException {
        tagDAO.deleteCertificateTag(certificateId);
    }

    @Override
    public void delete(Long id) throws TagServiceException, TagNotFoundException {
        try {
            getTagById(id);
            tagDAO.delete(id);
        } catch (DaoDeleteException e) {
            throw new TagServiceException("Deleting tag " + id + " service error", e);
        }
    }

    @Override
    public List<Tag> getTagsByCertificateId(Long id) {
        return tagDAO.getTagsByCertificateId(id);
    }
}
