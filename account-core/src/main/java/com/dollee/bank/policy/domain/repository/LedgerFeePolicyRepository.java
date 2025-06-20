package com.dollee.bank.policy.domain.repository;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import java.time.LocalDateTime;

public interface LedgerFeePolicyRepository {
  LedgerFeePolicy save(LedgerFeePolicy save);

  LedgerFeePolicy findById(String policyId);

  void delete(String policyId);

  LedgerFeePolicy getActivePolicyOrDefault(LedgerType ledgerType, LocalDateTime baseTime);
}
