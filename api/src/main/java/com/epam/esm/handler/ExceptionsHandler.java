package com.epam.esm.handler;

import com.epam.esm.exception.GiftCertificateServiceException;
import com.epam.esm.exception.TagServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = TagServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity tagServiceException(TagServiceException tse){
        return new ErrorEntity(tse.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = GiftCertificateServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity giftCertificateServiceException(GiftCertificateServiceException e){
        return new ErrorEntity(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
