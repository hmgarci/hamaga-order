package com.hamaga.order.exceptions;

import org.springframework.http.HttpStatus;

public class SaveOrderException extends DomainException {
    public SaveOrderException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
