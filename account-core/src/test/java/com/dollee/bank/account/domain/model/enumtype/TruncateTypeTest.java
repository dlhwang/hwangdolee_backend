package com.dollee.bank.account.domain.model.enumtype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TruncateTypeTest {

  @DisplayName("NO 절사 테스트")
  @Test
  void not_truncated() {
    assertEquals(123L, TruncateType.NO.truncate(123L));
    assertEquals(0L, TruncateType.NO.truncate(0L));
    assertEquals(-123L, TruncateType.NO.truncate(-123L));
  }

  @DisplayName("ONE 절사 테스트 (1원 단위 절사)")
  @Test
  void Truncate_to_the_nearest_1() {
    assertEquals(120L, TruncateType.ONE.truncate(123L));
    assertEquals(0L, TruncateType.ONE.truncate(0L));
    assertEquals(-130L, TruncateType.ONE.truncate(-123L));
  }

  @DisplayName("TEN 절사 테스트 (10원 단위 절사)")
  @Test
  void Truncate_to_the_nearest_10() {
    assertEquals(100L, TruncateType.TEN.truncate(129L));
    assertEquals(0L, TruncateType.TEN.truncate(19L));
    assertEquals(0L, TruncateType.TEN.truncate(7L));
    assertEquals(-100L, TruncateType.TEN.truncate(-7L));
    assertEquals(-100L, TruncateType.TEN.truncate(-17L));
  }
}
