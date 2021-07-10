package com.epam.esm.handler;

import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.GiftCertificateServiceException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.exception.TagServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = TagServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorEntity tagServiceException(TagServiceException e){
        return new ErrorEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = GiftCertificateServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorEntity giftCertificateServiceException(GiftCertificateServiceException e){
        return new ErrorEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = TagNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorEntity tagNotFoundException(TagNotFoundException e){
        return new ErrorEntity(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = GiftCertificateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorEntity giftCertificateNotFoundException(GiftCertificateNotFoundException e){
        return new ErrorEntity(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
