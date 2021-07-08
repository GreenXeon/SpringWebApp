package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoCreateException;
import com.epam.esm.exception.DaoDeleteException;
import com.epam.esm.exception.TagNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TagDAO {
    Tag create(Tag tag) throws DaoCreateException;

    List<Tag> getAllTags();

    Tag getTagByName(String name) throws TagNotFoundException;

    void delete(Long id) throws DaoDeleteException;
}
