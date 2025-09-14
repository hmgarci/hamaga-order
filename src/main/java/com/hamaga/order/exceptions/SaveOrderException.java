package com.hamaga.order.exceptions;

import com.hamaga.order.exceptions.messages.ErrorCode;
import org.springframework.http.HttpStatus;

public class SaveOrderException extends DomainException {
    public SaveOrderException(ErrorCode message, Object... args) {
        super(message, args);
    }
}
