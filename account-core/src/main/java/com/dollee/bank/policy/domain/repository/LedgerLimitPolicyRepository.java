package com.dollee.bank.policy.domain.repository;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.domain.model.LedgerLimitPolicy;
import java.time.LocalDateTime;

public interface LedgerLimitPolicyRepository {
  LedgerLimitPolicy save(LedgerLimitPolicy save);

  LedgerLimitPolicy findById(String policyId);

  void delete(String policyId);

  LedgerLimitPolicy getActivePolicyOrDefault(LedgerType ledgerType, LocalDateTime baseTime);
}
