package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.*;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.util.ServiceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {
    @Mock
    private GiftCertificateDAO giftCertificateDAO;

    @Mock
    private TagService tagService;

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    private GiftCertificate getTestCertificate(){
        Set<Tag> tagSet = new HashSet<>();
        Tag tag = new Tag.Builder()
                .withName("sample")
                .build();
        tagSet.add(tag);
        return new GiftCertificate.Builder()
                .withName("New")
                .withDescription("Sample description")
                .withPrice(BigDecimal.ONE)
                .withDuration(4)
                .withCreateDate(ServiceUtils.getCurrentDateTime())
                .withLastUpdateDate(ServiceUtils.getCurrentDateTime())
                .withTags(tagSet)
                .build();
    }

    @Test
    void whenMockCreatesCertificateThenReturnCertificate()
            throws DaoCreateException, GiftCertificateServiceException,
            TagAlreadyExistsException, GiftCertificateAlreadyExistsException {
        GiftCertificate giftCertificate = getTestCertificate();
        when(giftCertificateDAO.create(giftCertificate)).thenReturn(giftCertificate);
        when(tagService.getTagByName(anyString())).thenReturn(new Tag());
        lenient().doNothing().when(tagService).saveCertificateTag(anyLong(), anyLong());
        GiftCertificate newCertificate = giftCertificateService.create(giftCertificate);
        assertEquals(newCertificate.getDuration(), 4);
    }

    @Test
    void whenMockGetsAllCertificatesThenReturnList() {
        when(giftCertificateDAO.getAllCertificates()).thenReturn(new LinkedList<>());
        List<GiftCertificate> allTags = giftCertificateService.getAllCertificates();
        assertEquals(allTags, Collections.emptyList());
    }

    @Test
    void whenMockGetsCertificateByIdThenReturnNotNullCertificate()
            throws GiftCertificateNotFoundException {
        when(giftCertificateDAO.getCertificateById(anyLong())).thenReturn(new GiftCertificate());
        GiftCertificate giftCertificate = giftCertificateService.getCertificateById(1L);
        assertNotNull(giftCertificate);
    }

    @Test
    void whenMockGetsCertificateByTagNameThenReturnCertificate()
            throws GiftCertificateNotFoundException{
        when(giftCertificateDAO.getCertificateByTagName(anyString())).thenReturn(new GiftCertificate());
        GiftCertificate giftCertificate = giftCertificateService.getCertificateByTagName("samplename");
        assertNotNull(giftCertificate);
    }

    @Test
    void whenMockUpdatesCertificateThenReturnNewCertificate()
            throws DaoUpdateException, GiftCertificateNotFoundException, GiftCertificateServiceException,
            TagAlreadyExistsException, GiftCertificateAlreadyExistsException, DaoCreateException {
        GiftCertificate newCertificate = getTestCertificate();
        GiftCertificate oldCertificate = getTestCertificate();
        oldCertificate.setName("Old");
        newCertificate.setName("New");
        doReturn(newCertificate).when(giftCertificateDAO).update(1L, newCertificate);
        doReturn(oldCertificate).when(giftCertificateDAO).getCertificateById(anyLong());
        when(tagService.getTagByName(anyString())).thenReturn(new Tag());
        lenient().doNothing().when(tagService).saveCertificateTag(anyLong(), anyLong());
        GiftCertificate giftCertificate = giftCertificateService.update(1L, newCertificate);
        assertEquals(giftCertificate.getName(), "New");
    }

    @Test
    void whenMockDeletesTagThenReturnTrue() throws
            DaoDeleteException, GiftCertificateServiceException, GiftCertificateNotFoundException {
        doNothing().when(giftCertificateDAO).delete(anyLong());
        when(giftCertificateDAO.getCertificateById(anyLong())).thenReturn(new GiftCertificate());
        giftCertificateService.delete(1L);
        verify(giftCertificateDAO).delete(1L);
    }
}