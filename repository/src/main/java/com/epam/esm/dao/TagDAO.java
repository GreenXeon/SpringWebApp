package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoCreateException;
import com.epam.esm.exception.DaoDeleteException;

import java.util.List;

public interface TagDAO {
    Tag create(Tag tag) throws DaoCreateException;

    List<Tag> getAllTags();

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    void saveCertificateTag(Long certificateId, Long tagId) throws DaoCreateException;

    void deleteCertificateTag(Long certificateId) throws DaoDeleteException;

    void delete(Long id) throws DaoDeleteException;

    List<Tag> getTagsByCertificateId(Long id);
}
