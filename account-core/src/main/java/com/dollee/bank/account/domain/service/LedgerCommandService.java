package com.dollee.bank.account.domain.service;

import com.dollee.bank.account.domain.command.LedgerCommand;
import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;
import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.account.domain.repository.AccountRepository;
import com.dollee.bank.account.domain.repository.LedgerRepository;
import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import com.dollee.bank.policy.domain.model.LedgerLimitPolicy;
import com.dollee.bank.policy.domain.service.LedgerFeePolicyService;
import com.dollee.bank.policy.domain.service.LedgerLimitPolicyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class LedgerCommandService<T extends LedgerCommand> {

  private final LedgerRepository repository;
  private final AccountRepository accountRepository;
  private final LedgerFeePolicyService feePolicyService;
  private final LedgerLimitPolicyService limitPolicyService;

  public Ledger process(T command) {
    final LedgerFeePolicy activeFeePolicy = feePolicyService.getActivePolicyOrDefault(
        command.getLedgerType(), command.getOccurredAt());
    final LedgerLimitPolicy activeLimitPolicy = limitPolicyService.getActivePolicyOrDefault(
        command.getLedgerType(), command.getOccurredAt());

    final Account account = accountRepository.findByAccountNumberAndUserId(command.getLedgerType(),
        command.getAccountNumber(), command.getExecutedBy());

    validateLimit(command.getLedgerType(), account, activeLimitPolicy);

    LedgerDetail ledgerDetail = LedgerDetail.newInstance(command.getLedgerType(),
        command.getOccurredAt(), command.getAmount(),
        command.getDescription(), command.getExecutedBy());

    LedgerFeeDetail feeDetail = activeFeePolicy.calculate(ledgerDetail);

    updateAccountBalance(account, command.getAmount());
    accountRepository.save(account);

    return repository.save(Ledger.newInstance(account.getAccountId(), ledgerDetail,
        account.getAccountDetail(), feeDetail), account);
  }

  private void validateLimit(LedgerType type, Account account, LedgerLimitPolicy policy) {
    final long sumByCycle = repository.getSumByCycle(account.getAccountId(), policy.getCycle());
    if (sumByCycle > policy.getAmount()) {
      throw new IllegalArgumentException(getLimitExceededMessage(type, policy));
    }
  }

  private String getLimitExceededMessage(LedgerType type, LedgerLimitPolicy policy) {
    return String.format("%s %s 한도를 초과하였습니다.(%,d원)",
        policy.getCycle().getName(), type.getName(), policy.getAmount());
  }

  protected abstract void updateAccountBalance(Account account, long amount);

}
