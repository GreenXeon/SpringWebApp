package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.*;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Tag create(Tag tag) throws TagServiceException, TagAlreadyExistsException;

    List<Tag> getAllTags();

    Tag getTagById(Long id) throws TagServiceException, TagNotFoundException;

    Tag getTagByName(String name);

    void saveCertificateTag(Long certificateId, Long tagId) throws DaoCreateException;

    void deleteCertificateTag(Long certificateId) throws DaoDeleteException;

    void delete(Long id) throws TagServiceException, TagNotFoundException;

    List<Tag> getTagsByCertificateId(Long id);
}
