package com.dollee.bank.common.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Cycle {
  NONE("없음"),
  DAILY("일별"),
  WEEKLY("주별"),
  MONTHLY("월별");
  private final String name;
}
