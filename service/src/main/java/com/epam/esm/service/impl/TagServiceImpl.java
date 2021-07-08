package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoCreateException;
import com.epam.esm.exception.DaoDeleteException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.exception.TagServiceException;
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
    public Tag create(Tag tag) throws TagServiceException {
        Tag createdTag;
        try {
            createdTag = tagDAO.create(tag);
        } catch (DaoCreateException e) {
            throw new TagServiceException("Creating tag " + tag.getName() + " service error", e);
        }
        return createdTag;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDAO.getAllTags();
    }

    @Override
    public Tag getTagByName(String name) throws TagServiceException {
        try {
            return tagDAO.getTagByName(name);
        } catch (TagNotFoundException e) {
            throw new TagServiceException("Getting tag " + name + " service error", e);
        }
    }

    @Override
    public void delete(Long id) throws TagServiceException {
        try {
            tagDAO.delete(id);
        } catch (DaoDeleteException e) {
            throw new TagServiceException("Deleting tag " + id + " service error", e);
        }
    }
}
