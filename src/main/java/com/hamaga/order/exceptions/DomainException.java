package com.hamaga.order.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DomainException extends RuntimeException {
    private final HttpStatus status;

    protected DomainException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
