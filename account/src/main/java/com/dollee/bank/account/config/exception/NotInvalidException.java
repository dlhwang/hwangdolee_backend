package com.dollee.bank.account.config.exception;

import java.io.Serial;

public class NotInvalidException extends RuntimeException {
  @Serial private static final long serialVersionUID = 6371307594005060125L;

  public NotInvalidException(String message) {
    super(message);
  }

  public NotInvalidException() {}
}
