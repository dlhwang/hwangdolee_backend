package com.dollee.bank.policy.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.domain.model.enumtype.FeeType;
import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LedgerFeePolicyTest {
  @Test
  @DisplayName("TRANSFER 기본 수수료 정책은 정율 1%, 절사 없음, 정액 0원이다")
  void testCreateDefaultForTransfer() {
    LedgerFeePolicy domain =
        LedgerFeePolicy.createDefault(LedgerType.TRANSFER, LocalDateTime.of(2025, 6, 18, 0, 0));

    LedgerFeePolicyDetail detail = domain.getLedgerFeePolicyDetail();

    assertThat(detail.getFeeType()).isEqualTo(FeeType.정율);
    assertThat(detail.getRate()).isEqualTo(0.01f);
    assertThat(detail.getAmount()).isEqualTo(0L);
    assertThat(detail.getTruncateType()).isEqualTo(TruncateType.NO);
  }

  @Test
  @DisplayName("TRANSFER 이외의 기본 수수료 정책은 정책 없음이 적용된다")
  void testCreateDefaultForWithoutTransfer() {
    LedgerFeePolicy domain =
        LedgerFeePolicy.createDefault(LedgerType.DEPOSIT, LocalDateTime.of(2025, 6, 18, 0, 0));

    LedgerFeePolicyDetail detail = domain.getLedgerFeePolicyDetail();

    assertThat(detail.getFeeType()).isEqualTo(FeeType.없음);
    assertThat(detail.getRate()).isEqualTo(0f);
    assertThat(detail.getAmount()).isEqualTo(0L);
    assertThat(detail.getTruncateType()).isEqualTo(TruncateType.NO);
  }
}
