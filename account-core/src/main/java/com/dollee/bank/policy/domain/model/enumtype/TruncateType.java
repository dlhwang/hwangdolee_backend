package com.dollee.bank.policy.domain.model.enumtype;


import java.util.function.Function;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public enum TruncateType {
  NO("절사하지않음", (number) -> truncate(number, 1)),
  ONE("1원단위 절사", (number) -> truncate(number, 10)),
  TEN("10원단위 절사", (number) -> truncate(number, 100));

  private String title;
  private Function<Long, Long> truncateStrategy;

  TruncateType(String title, Function<Long, Long> truncateStrategy) {
    this.title = title;
    this.truncateStrategy = truncateStrategy;
  }

  public long truncate(long number) {
    return truncateStrategy.apply(number);
  }

  private static long truncate(long number, int divider) {
    Assert.isTrue(divider > 0, "0으로 나눌 수 없습니다.");
    return Math.floorDiv(number, divider) * divider;
  }
}
