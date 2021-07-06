package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagServiceImpl implements TagService {
    private final TagDAOImpl tagDAO;

    @Autowired
    public TagServiceImpl(TagDAOImpl tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDAO.getAllTags();
    }
}
