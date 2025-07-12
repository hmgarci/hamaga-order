package com.hamaga.order.exceptions;

import org.springframework.http.HttpStatus;

public class StockNoAvailableException extends DomainException {
  public StockNoAvailableException(String message) {
    super(message, HttpStatus.CONFLICT);
  }
}
