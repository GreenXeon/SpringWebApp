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
        try {
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
    public void delete(Long id) throws TagServiceException, TagNotFoundException {
        try {
            getTagById(id);
            tagDAO.delete(id);
        } catch (DaoDeleteException e) {
            throw new TagServiceException("Deleting tag " + id + " service error", e);
        }
    }
}
