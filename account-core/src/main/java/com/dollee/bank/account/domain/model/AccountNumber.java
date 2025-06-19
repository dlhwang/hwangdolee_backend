package com.dollee.bank.account.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA용 기본 생성자
public class AccountNumber {
  private String number;

  public static AccountNumber to(String value) {
    return new AccountNumber(value);
  }

}
