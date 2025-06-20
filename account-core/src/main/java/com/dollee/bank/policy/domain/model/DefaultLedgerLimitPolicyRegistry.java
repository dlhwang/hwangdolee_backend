package com.dollee.bank.policy.domain.model;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.common.enumtype.Cycle;
import java.util.Map;

public class DefaultLedgerLimitPolicyRegistry {

  private static final Map<LedgerType, LedgerLimitPolicyDetail> DEFAULT_POLICIES =
      Map.of(
          LedgerType.WITHDRAWAL, LedgerLimitPolicyDetail.newInstance(Cycle.DAILY, 1_000_000L),
          LedgerType.TRANSFER, LedgerLimitPolicyDetail.newInstance(Cycle.DAILY, 3_000_000L));

  public static LedgerLimitPolicyDetail getDefault(LedgerType type) {
    return DEFAULT_POLICIES.getOrDefault(type, LedgerLimitPolicyDetail.newInstance(Cycle.NONE, 0L));
  }
}
