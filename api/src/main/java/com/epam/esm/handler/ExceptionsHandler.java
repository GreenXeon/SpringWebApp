package com.epam.esm.handler;

import com.epam.esm.exception.*;
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

    @ExceptionHandler(value = TagAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorEntity tagAlreadyExistsException(TagAlreadyExistsException e){
        return new ErrorEntity(e.getMessage(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(value = GiftCertificateAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorEntity giftCertificateAlreadyExistsException(GiftCertificateAlreadyExistsException e){
        return new ErrorEntity(e.getMessage(), HttpStatus.CONFLICT.value());
    }
}
