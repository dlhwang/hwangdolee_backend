package com.dollee.bank.account.domain.service;

import com.dollee.bank.account.domain.command.DepositCommand;
import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;
import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.account.domain.repository.AccountRepository;
import com.dollee.bank.account.domain.repository.LedgerRepository;
import com.dollee.bank.common.logging.Loggable;
import com.dollee.bank.common.util.Money;
import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import com.dollee.bank.policy.domain.model.LedgerLimitPolicy;
import com.dollee.bank.policy.domain.service.LedgerFeePolicyService;
import com.dollee.bank.policy.domain.service.LedgerLimitPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LedgerService {

  private final LedgerRepository repository;
  private final AccountRepository accountRepository;
  private final LedgerFeePolicyService feePolicyService;
  private final LedgerLimitPolicyService limitPolicyService;

  @Loggable
  @Transactional
  public Ledger deposit(DepositCommand command) {
    final LedgerFeePolicy activeFeePolicy = feePolicyService.getActivePolicyOrDefault(
        command.getLedgerType(), command.getOccurredAt());
    final LedgerLimitPolicy activeLimitPolicy = limitPolicyService.getActivePolicyOrDefault(
        command.getLedgerType(), command.getOccurredAt());

    final Account account = accountRepository.findByAccountNumber(command.getAccountNumber());

    // 한도 검사
    validateLimit(command.getLedgerType(), account, activeLimitPolicy);

    // 대장 작성
    LedgerDetail ledgerDetail = LedgerDetail.newInstance(command.getLedgerType(),
        command.getOccurredAt(), command.getAmount(),
        command.getDescription(), command.getExecutedBy());

    // 수수료 계산
    LedgerFeeDetail feeDetail = activeFeePolicy.calculate(ledgerDetail);

    // 입금
    account.increaseBalance(Money.wons(command.getAmount()));
    accountRepository.save(account);
    return repository.save(Ledger.newInstance(account.getAccountId(), ledgerDetail,
        account.getAccountDetail(), feeDetail), account);
  }

  private void validateLimit(LedgerType type, Account account,
      LedgerLimitPolicy activeLimitPolicy) {
    log.info("#### amount :{} , Cycle {}", activeLimitPolicy.getAmount(),
        activeLimitPolicy.getCycle());
    final long sumByCycle = repository.getSumByCycle(account.getAccountId(),
        activeLimitPolicy.getCycle());
    if (sumByCycle > activeLimitPolicy.getAmount()) {
      throw new IllegalArgumentException(getString(type, activeLimitPolicy));
    }
  }

  private String getString(LedgerType type, LedgerLimitPolicy activeLimitPolicy) {
    return String.format("%s %s 한도를 초과하였습니다.(%,d원)",
        activeLimitPolicy.getCycle().getName(),
        type.getName(),
        activeLimitPolicy.getAmount()
    );
  }

  void withdrawal() {
  }

  void transfer() {
  }
}
