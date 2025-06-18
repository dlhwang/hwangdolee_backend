package com.dollee.bank.policy.domain.model;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
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

  public boolean isEffective(){
    return LocalDateTime.now().isBefore(effectiveFrom);
  }
}
