package com.dollee.bank.policy.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.common.enumtype.Cycle;
import com.dollee.bank.policy.domain.model.enumtype.FeeType;
import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
import com.dollee.bank.policy.infra.entity.LedgerFeePolicyEntity;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LedgerLimitPolicyTest {
  @Test
  @DisplayName("WITHDRAWAL 기본 제한 정책은 일별 1,000,000원 이다")
  void testCreateDefaultForWithdrawal() {
    LedgerLimitPolicy domain = LedgerLimitPolicy.createDefault(
        LedgerType.WITHDRAWAL,
        LocalDateTime.of(2025, 6, 18, 0, 0)
    );

    LedgerLimitPolicyDetail detail = domain.getLedgerLimitPolicyDetail();

    assertThat(detail.getCycle()).isEqualTo(Cycle.DAILY);
    assertThat(detail.getAmount()).isEqualTo(1_000_000L);
  }

  @Test
  @DisplayName("TRANSFER 기본 제한 정책은 일별 3,000,000원 이다")
  void testCreateDefaultForTransfer() {
    LedgerLimitPolicy domain = LedgerLimitPolicy.createDefault(
        LedgerType.TRANSFER,
        LocalDateTime.of(2025, 6, 18, 0, 0)
    );

    LedgerLimitPolicyDetail detail = domain.getLedgerLimitPolicyDetail();

    assertThat(detail.getCycle()).isEqualTo(Cycle.DAILY);
    assertThat(detail.getAmount()).isEqualTo(3_000_000L);
  }

  @Test
  @DisplayName("DEPOSIT 기본 제한 정책은 없다")
  void testCreateDefaultForDeposit() {
    LedgerLimitPolicy domain = LedgerLimitPolicy.createDefault(
        LedgerType.DEPOSIT,
        LocalDateTime.of(2025, 6, 18, 0, 0)
    );

    LedgerLimitPolicyDetail detail = domain.getLedgerLimitPolicyDetail();

    assertThat(detail.getCycle()).isEqualTo(Cycle.NONE);
    assertThat(detail.getAmount()).isEqualTo(0L);
  }
}