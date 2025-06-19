package com.dollee.bank.account.domain.model.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LedgerType {
  DEPOSIT("입금"),
  WITHDRAWAL("출금"),
  TRANSFER("송금");
  private final String name;
}
