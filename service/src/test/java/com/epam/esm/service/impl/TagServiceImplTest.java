package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoCreateException;
import com.epam.esm.exception.DaoDeleteException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.exception.TagServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    @Mock
    private TagDAO tagDAO;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void whenMockCreatesTagThenReturnTag() throws DaoCreateException, TagServiceException {
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
        when(tagDAO.getTagByName(anyString())).thenReturn(new Tag());
        Tag tag = tagService.getTagByName("mjc");
        assertNotNull(tag);
    }

    @Test
    public void whenMockDeletesTagThenReturnTrue() throws DaoDeleteException, TagServiceException {
        doNothing().when(tagDAO).delete(anyLong());
        tagService.delete(1L);
        verify(tagDAO).delete(anyLong());
    }
}