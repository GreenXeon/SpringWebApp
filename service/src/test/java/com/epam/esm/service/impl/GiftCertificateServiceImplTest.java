package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.*;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.util.ServiceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {
    @Mock
    private GiftCertificateDAO giftCertificateDAO;

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    private GiftCertificate getTestCertificate(){
        return new GiftCertificate.Builder()
                .withName("New")
                .withDescription("Sample description")
                .withPrice(BigDecimal.ONE)
                .withDuration(4)
                .withCreateDate(ServiceUtils.getCurrentDateTime())
                .withLastUpdateDate(ServiceUtils.getCurrentDateTime())
                .build();
    }

    @Test
    void whenMockCreatesCertificateThenReturnCertificate() throws DaoCreateException, GiftCertificateServiceException {
        GiftCertificate giftCertificate = getTestCertificate();
        when(giftCertificateDAO.create(giftCertificate)).thenReturn(giftCertificate);
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
            throws GiftCertificateNotFoundException, GiftCertificateServiceException {
        when(giftCertificateDAO.getCertificateById(anyLong())).thenReturn(new GiftCertificate());
        GiftCertificate giftCertificate = giftCertificateService.getCertificateById(1L);
        assertNotNull(giftCertificate);
    }

    @Test
    void whenMockGetsCertificateByTagNameThenReturnCertificate()
            throws GiftCertificateNotFoundException, GiftCertificateServiceException {
        when(giftCertificateDAO.getCertificateByTagName(anyString())).thenReturn(new GiftCertificate());
        GiftCertificate giftCertificate = giftCertificateService.getCertificateByTagName("samplename");
        assertNotNull(giftCertificate);
    }

    @Test
    void whenMockUpdatesCertificateThenReturnNewCertificate()
            throws DaoUpdateException, GiftCertificateNotFoundException, GiftCertificateServiceException {
        GiftCertificate newCertificate = getTestCertificate();
        GiftCertificate oldCertificate = getTestCertificate();
        oldCertificate.setName(null);
        newCertificate.setName("New");
        doReturn(newCertificate).when(giftCertificateDAO).update(1L, newCertificate);
        doReturn(oldCertificate).when(giftCertificateDAO).getCertificateById(1L);
        GiftCertificate giftCertificate = giftCertificateService.update(1L, newCertificate);
        assertEquals(giftCertificate.getName(), "New");
    }

    @Test
    void whenMockDeletesTagThenReturnTrue() throws DaoDeleteException, GiftCertificateServiceException {
        doNothing().when(giftCertificateDAO).delete(anyLong());
        giftCertificateService.delete(1L);
        verify(giftCertificateDAO).delete(1L);
    }
}