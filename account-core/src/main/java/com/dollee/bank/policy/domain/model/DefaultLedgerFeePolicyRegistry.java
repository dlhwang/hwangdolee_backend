package com.dollee.bank.policy.domain.model;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.domain.model.enumtype.FeeType;
import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
import java.util.Map;

public class DefaultLedgerFeePolicyRegistry {

  private static final Map<LedgerType, LedgerFeePolicyDetail> DEFAULT_POLICIES =
      Map.of(
          LedgerType.TRANSFER,
          LedgerFeePolicyDetail.newInstance(FeeType.정율, TruncateType.NO, 0.01f, 0L));

  public static LedgerFeePolicyDetail getDefault(LedgerType type) {
    return DEFAULT_POLICIES.getOrDefault(
        type, LedgerFeePolicyDetail.newInstance(FeeType.없음, TruncateType.NO, 0f, 0L));
  }
}
