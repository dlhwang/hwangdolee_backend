package com.dollee.bank.account.domain.service;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.account.domain.repository.LedgerRepository;
import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import com.dollee.bank.policy.domain.model.LedgerLimitPolicy;
import com.dollee.bank.policy.domain.service.LedgerFeePolicyService;
import com.dollee.bank.policy.domain.service.LedgerLimitPolicyService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LedgerService {

  private final LedgerRepository repository;
  private final LedgerFeePolicyService feePolicyService;
  private final LedgerLimitPolicyService limitPolicyService;

  void deposit() {
    LedgerType type = LedgerType.DEPOSIT;
    LocalDateTime now = LocalDateTime.now();
    LedgerFeePolicy activeFeePolicy = feePolicyService.getActivePolicyOrDefault(type, now);
    LedgerLimitPolicy activeLimitPolicy = limitPolicyService.getActivePolicyOrDefault(type, now);
  }

  void withdrawal() {
  }

  void transfer() {
  }
}
