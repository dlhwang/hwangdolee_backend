package com.dollee.bank.policy.domain.model.enumtype;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeeTypeTest {

  @Test
  @DisplayName("수수료 타입: 없음은 항상 0을 반환한다")
  void testNoneFee() {
    long result = FeeType.없음.calculate(10_000L, 1_000L, 0.1, TruncateType.NO);
    assertThat(result).isZero();
  }

  @Test
  @DisplayName("정액 수수료는 feeValue 그대로 절사 후 반환한다")
  void testFixedFeeNoTruncate() {
    long result = FeeType.정액.calculate(10_000L, 1234L, 0.0, TruncateType.NO);
    assertThat(result).isEqualTo(1234L);
  }

  @Test
  @DisplayName("정액 수수료는 절사 정책이 적용된다")
  void testFixedFeeWithTruncate() {
    long result = FeeType.정액.calculate(10_000L, 1234L, 0.0, TruncateType.ONE); // 1234 → 1230
    assertThat(result).isEqualTo(1230L);
  }

  @Test
  @DisplayName("정율 수수료는 amount * rate 후 절사 정책이 적용된다")
  void testRateFeeNoTruncate() {
    long result = FeeType.정율.calculate(10_000L, 0L, 0.035, TruncateType.NO); // 10,000 * 0.035 = 350
    assertThat(result).isEqualTo(350L);
  }

  @Test
  @DisplayName("정율 수수료는 절사 정책이 적용된다")
  void testRateFeeWithTruncate() {
    long result = FeeType.정율.calculate(10_000L, 0L, 0.037, TruncateType.NO); // 370 → 370 → 370
    assertThat(result).isEqualTo(370L);

    long result2 = FeeType.정율.calculate(10_000L, 0L, 0.039, TruncateType.ONE); // 390 → 390 → 390
    assertThat(result2).isEqualTo(390L);

    long result3 = FeeType.정율.calculate(10_000L, 0L, 0.038, TruncateType.TEN); // 380 → 380 → 300
    assertThat(result3).isEqualTo(300L);
  }
}