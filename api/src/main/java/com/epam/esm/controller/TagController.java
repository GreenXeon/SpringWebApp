package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.TagAlreadyExistsException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.exception.TagServiceException;
import com.epam.esm.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 *
 * Class represents user API for operations with tags
 * @author Zakhar Shyshkin
 * @since 1.0
 *
 */

@RestController
@EnableWebMvc
@RequestMapping(value = "/tag", produces = "application/json")
public class TagController {

    private final Logger logger = LogManager.getLogger(TagController.class);

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Shows all tags
     *
     * @return response entity with tags and HTTP OK status
     */

    @GetMapping
    public ResponseEntity<List<Tag>> showAllTags()
    {
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    /**
     * Shows tag with certain id
     *
     * @param id identifier of tag
     * @return response entity with found tag and OK HTTP status
     * @throws TagServiceException if error occurred during service processes
     * @throws TagNotFoundException if tag with given id was not found
     */

    @GetMapping("/{id}")
    public ResponseEntity<Tag> findById(@PathVariable Long id) throws TagServiceException, TagNotFoundException {
        return new ResponseEntity<>(tagService.getTagById(id), HttpStatus.OK);
    }

    /**
     * Creates tag, taken from request body
     *
     * @param tag tag for creating
     * @return response entity with created tag and CREATED HTTP status
     * @throws TagServiceException if error occurred during service processes
     * @throws TagAlreadyExistsException if tag with given name already exists
     */

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) throws TagServiceException, TagAlreadyExistsException {
        Tag newTag = tagService.create(tag);
        return new ResponseEntity<>(newTag, HttpStatus.CREATED);
    }

    /**
     * Deletes tag by certain id
     *
     * @param id tag id
     * @return response entity with NO_CONTENT HTTP status
     * @throws TagServiceException if error occurred during service processes
     * @throws TagNotFoundException if tag with given id was not found
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable Long id) throws TagServiceException, TagNotFoundException {
        tagService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
