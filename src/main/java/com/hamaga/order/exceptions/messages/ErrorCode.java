package com.hamaga.order.exceptions.messages;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
    STOCK_NOT_AVAILABLE("ERR-001", "Stock not available for product: %s", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND("ERR-002", "Product %s not found", HttpStatus.NOT_FOUND),
    ERROR_TO_SAVE_ORDER("ERR-003", "Error to save order: %s", HttpStatus.INTERNAL_SERVER_ERROR);

    @Getter
    private final String code;
    private final String message;
    @Getter
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String formatMessage(Object... args) {
        return String.format(message, args);
    }

}
