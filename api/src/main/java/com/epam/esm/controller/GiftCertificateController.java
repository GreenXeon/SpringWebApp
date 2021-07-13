package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.GiftCertificateAlreadyExistsException;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.GiftCertificateServiceException;
import com.epam.esm.exception.TagAlreadyExistsException;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Class represents user API for operations with certificates
 * @author Zakhar Shyshkin
 * @since 1.0
 *
 */

@RestController
@EnableWebMvc
@RequestMapping(value = "/certificate")
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    /**
     * Shows all certificates with tags
     *
     * @param name name of tag, part of description or part of certificate name
     * @param order asc/desc
     * @return response entity with list of found tags
     */

    @GetMapping
    public ResponseEntity<List<GiftCertificate>> showAllCertificates(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String order)
    {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.computeIfAbsent("name", val -> name);
        queryParams.computeIfAbsent("order", val -> order);
        return new ResponseEntity<>(giftCertificateService.getAllCertificatesByQuery(queryParams), HttpStatus.OK);
    }

    /**
     * Shows certificate with certain id
     *
     * @param id tag id
     * @return response entity with found certificate and OK HTTP status
     *
     * @throws GiftCertificateServiceException if error occurred during service processes
     * @throws GiftCertificateNotFoundException if certificate with given id was not found
     */

    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificate> showCertificateById(@PathVariable Long id)
            throws GiftCertificateServiceException, GiftCertificateNotFoundException {
        GiftCertificate giftCertificate = giftCertificateService.getCertificateById(id);
        return new ResponseEntity<>(giftCertificate, HttpStatus.OK);
    }

    /**
     * Creates certificate, taken from request body
     *
     * @param giftCertificate certificate for creating
     * @return response entity with created certificate and CREATED HTTP status
     *
     * @throws GiftCertificateServiceException if error occurred during service processes
     * @throws TagAlreadyExistsException if tag with given name already exists
     * @throws GiftCertificateAlreadyExistsException if certificate with given name already exists
     */

    @PostMapping
    public ResponseEntity<GiftCertificate> createCertificate(@RequestBody GiftCertificate giftCertificate)
            throws GiftCertificateServiceException, TagAlreadyExistsException, GiftCertificateAlreadyExistsException {
        GiftCertificate newCertificate = giftCertificateService.create(giftCertificate);
        return new ResponseEntity<>(newCertificate, HttpStatus.CREATED);
    }

    /**
     * Deletes certificate with certain id
     *
     * @param id certificate id
     * @return response entity with NO_CONTENT HTTP status
     *
     * @throws GiftCertificateServiceException if error occurred during service processes
     * @throws GiftCertificateNotFoundException if certificate with given id was not found
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCertificate(@PathVariable Long id)
            throws GiftCertificateServiceException, GiftCertificateNotFoundException {
        giftCertificateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates certificate with certain id
     *
     * @param id certificate id
     * @param giftCertificate entity with fields for updating
     * @return response entity with updated certificate and OK HTTP status
     *
     * @throws GiftCertificateServiceException if error occurred during service processes
     * @throws GiftCertificateNotFoundException if certificate with given id was not found
     * @throws TagAlreadyExistsException if tag with given name already exists
     * @throws GiftCertificateAlreadyExistsException if certificate with given name already exists
     */

    @PatchMapping("/{id}")
    public ResponseEntity<GiftCertificate> updateCertificate(@PathVariable Long id,
                                                             @RequestBody GiftCertificate giftCertificate)
            throws GiftCertificateServiceException, GiftCertificateNotFoundException, TagAlreadyExistsException,
                GiftCertificateAlreadyExistsException {
        GiftCertificate newCertificate = giftCertificateService.update(id, giftCertificate);
        return new ResponseEntity<>(newCertificate, HttpStatus.OK);
    }
}