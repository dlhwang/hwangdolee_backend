package com.dollee.bank.common.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Cycle {
  NONE("없음"),
  DAILY("매일"),
  WEEKLY("매주"),
  MONTHLY("매월");
  private final String name;
}
