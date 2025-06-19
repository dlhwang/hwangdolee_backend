package com.dollee.bank.policy.domain.model;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.infra.entity.LedgerLimitPolicyEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerLimitPolicy {
  private String policyId;
  private LedgerType ledgerType;
  private LocalDateTime effectiveFrom;
  private LedgerLimitPolicyDetail ledgerLimitPolicyDetail;

  public static LedgerLimitPolicy of(String id, LedgerType ledgerType, LocalDateTime effectiveFrom,
      LedgerLimitPolicyDetail ledgerLimitPolicyDetail) {
    return new LedgerLimitPolicy(id, ledgerType, effectiveFrom, ledgerLimitPolicyDetail);
  }

  protected LedgerLimitPolicy(LedgerType ledgerType, LocalDateTime effectiveFrom,
      LedgerLimitPolicyDetail ledgerLimitPolicyDetail) {
    this.ledgerType = ledgerType;
    this.effectiveFrom = effectiveFrom;
    this.ledgerLimitPolicyDetail = ledgerLimitPolicyDetail;
  }

  public boolean isEffective(){
    return LocalDateTime.now().isBefore(effectiveFrom);
  }

  public static LedgerLimitPolicy createDefault(LedgerType ledgerType, LocalDateTime baseTime) {
    return new LedgerLimitPolicy(ledgerType, baseTime, DefaultLedgerLimitPolicyRegistry.getDefault(ledgerType));
  }
}
