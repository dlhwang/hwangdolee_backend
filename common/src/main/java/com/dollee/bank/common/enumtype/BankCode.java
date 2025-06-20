package com.dollee.bank.common.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BankCode {
  DOLLEE("8888"),
  SOMEBANK("9999");

  private final String code;
}
