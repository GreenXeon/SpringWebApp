package com.epam.esm.exception;

public class GiftCertificateServiceException extends Exception{
    public GiftCertificateServiceException(String message) {
        super(message);
    }

    public GiftCertificateServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
