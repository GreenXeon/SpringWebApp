package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    @Mock
    private TagDAO tagDAO;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void whenMockCreatesTagThenReturnTag()
            throws DaoCreateException, TagServiceException, TagAlreadyExistsException {
        Tag tag = new Tag.Builder()
                .withId(1L)
                .withName("SampleName")
                .build();
        when(tagDAO.create(tag)).thenReturn(tag);
        tagService.create(tag);
        assertEquals(tag.getName(), "SampleName");
    }

    @Test
    public void whenMockGetsAllTagsThenReturnEmptyList() {
        when(tagDAO.getAllTags()).thenReturn(new LinkedList<>());
        List<Tag> tags = tagService.getAllTags();
        assertNotNull(tags);
    }

    @Test
    public void whenMockGetsTagByNameThenReturnNotNullTag() throws TagServiceException, TagNotFoundException {
        when(tagDAO.getTagById(anyLong())).thenReturn(new Tag());
        Tag tag = tagService.getTagById(1L);
        assertNotNull(tag);
    }

    @Test
    public void whenMockDeletesTagThenReturnTrue() throws DaoDeleteException, TagServiceException, TagNotFoundException {
        doNothing().when(tagDAO).delete(anyLong());
        when(tagDAO.getTagById(anyLong())).thenReturn(new Tag());
        tagService.delete(17L);
        verify(tagDAO).delete(anyLong());
    }
}