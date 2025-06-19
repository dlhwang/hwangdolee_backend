package com.dollee.bank.policy.domain.service;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import com.dollee.bank.policy.domain.repository.LedgerFeePolicyRepository;
import com.dollee.bank.policy.infra.entity.LedgerFeePolicyEntity;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LedgerFeePolicyService {

  private final LedgerFeePolicyRepository repository;

  public LedgerFeePolicy getActivePolicyOrDefault(LedgerType ledgerType, LocalDateTime baseTime) {
    return repository.getActivePolicyOrDefault(ledgerType, baseTime);
  }

  public LedgerFeePolicy getActivePolicyOrDefault(LedgerType ledgerType) {
    return repository.getActivePolicyOrDefault(ledgerType, LocalDateTime.now());
  }


}
