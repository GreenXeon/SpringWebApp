package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoCreateException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.exception.TagServiceException;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Tag create(Tag tag) throws TagServiceException;

    List<Tag> getAllTags();

    Tag getTagById(Long id) throws TagServiceException, TagNotFoundException;

    void delete(Long id) throws TagServiceException, TagNotFoundException;
}
