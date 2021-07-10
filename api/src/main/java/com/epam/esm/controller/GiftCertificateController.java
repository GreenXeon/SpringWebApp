package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.GiftCertificateServiceException;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@EnableWebMvc
@RequestMapping(value = "/certificate")
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public ResponseEntity<List<GiftCertificate>> showAllCertificates(){
        return new ResponseEntity<>(giftCertificateService.getAllCertificates(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificate> showCertificateById(@PathVariable Long id)
            throws GiftCertificateServiceException, GiftCertificateNotFoundException {
        GiftCertificate giftCertificate = giftCertificateService.getCertificateById(id);
        return new ResponseEntity<>(giftCertificate, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GiftCertificate> createCertificate(@RequestBody GiftCertificate giftCertificate)
            throws GiftCertificateServiceException {
        GiftCertificate newCertificate = giftCertificateService.create(giftCertificate);
        return new ResponseEntity<>(newCertificate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCertificate(@PathVariable Long id)
            throws GiftCertificateServiceException, GiftCertificateNotFoundException {
        giftCertificateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GiftCertificate> updateCertificate(@PathVariable Long id,
                                                             @RequestBody GiftCertificate giftCertificate)
            throws GiftCertificateServiceException, GiftCertificateNotFoundException {
        GiftCertificate newCertificate = giftCertificateService.update(id, giftCertificate);
        return new ResponseEntity<>(newCertificate, HttpStatus.OK);
    }
}