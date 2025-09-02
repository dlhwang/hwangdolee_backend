package com.dollee.bank.policy.domain.model;

import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;
import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerFeePolicy {
  private String policyId;
  private LedgerType ledgerType;
  private LocalDateTime effectiveFrom;
  private LedgerFeePolicyDetail ledgerFeePolicyDetail;

  protected LedgerFeePolicy(
      LedgerType type, LocalDateTime effectiveFrom, LedgerFeePolicyDetail detail) {
    this.ledgerType = type;
    this.effectiveFrom = effectiveFrom;
    this.ledgerFeePolicyDetail = detail;
  }

  public static LedgerFeePolicy of(
      String id,
      LedgerType ledgerType,
      LocalDateTime effectiveFrom,
      LedgerFeePolicyDetail ledgerFeePolicyDetail) {
    return new LedgerFeePolicy(id, ledgerType, effectiveFrom, ledgerFeePolicyDetail);
  }

  public static LedgerFeePolicy createDefault(LedgerType ledgerType, LocalDateTime baseTime) {
    return new LedgerFeePolicy(
        ledgerType, baseTime, DefaultLedgerFeePolicyRegistry.getDefault(ledgerType));
  }

  public boolean isEffective() {
    return LocalDateTime.now().isBefore(effectiveFrom);
  }

  public LedgerFeeDetail calculate(LedgerDetail detail) {
    return ledgerFeePolicyDetail.calculate(detail);
  }
}
