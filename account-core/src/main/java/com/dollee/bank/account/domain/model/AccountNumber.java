package com.dollee.bank.account.domain.model;

import lombok.Getter;

@Getter
public class AccountNumber {
  private final String value;

  private AccountNumber(String value) {
    this.value = value;
  }

  public static AccountNumber to(String value) {
    return new AccountNumber(value);
  }

}
