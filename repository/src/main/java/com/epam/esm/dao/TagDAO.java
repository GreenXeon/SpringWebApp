package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagDAO {
    Tag create(Tag tag);

    List<Tag> getAllTags();

    Tag getTagByName(String name);

    void delete(int id);
}
