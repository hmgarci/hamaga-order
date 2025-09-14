package com.hamaga.order.exceptions;

import com.hamaga.order.exceptions.messages.ErrorCode;

public class StockNoAvailableException extends DomainException {
  public StockNoAvailableException(ErrorCode message, Object... args) {
    super(message, args);
  }
}
