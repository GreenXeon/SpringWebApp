package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDAO;
import junit.framework.TestCase;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest extends TestCase {
    @Mock
    private TagDAO tagDAO;

    @InjectMocks
    private TagServiceImpl tagService;

    public void testCreate() {
    }

    public void testGetAllTags() {
    }

    public void testGetTagByName() {
    }

    public void testDelete() {
    }
}