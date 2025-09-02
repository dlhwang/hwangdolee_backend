package com.dollee.bank.account.domain.service;

import com.dollee.bank.account.domain.command.DepositCommand;
import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.repository.AccountRepository;
import com.dollee.bank.account.domain.repository.LedgerRepository;
import com.dollee.bank.common.util.Money;
import com.dollee.bank.policy.domain.service.LedgerFeePolicyService;
import com.dollee.bank.policy.domain.service.LedgerLimitPolicyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepositCommandService extends LedgerCommandService<DepositCommand> {

  public DepositCommandService(LedgerRepository ledgerRepository,
      AccountRepository accountRepository,
      LedgerFeePolicyService feePolicyService,
      LedgerLimitPolicyService limitPolicyService
  ) {
    super(ledgerRepository, accountRepository, feePolicyService, limitPolicyService);
  }

  @Transactional
  @Override
  public Ledger process(DepositCommand command) {
    return super.process(command);
  }

  @Override
  protected void updateAccountBalance(Account account, long amount) {
    account.increaseBalance(Money.wons(amount));
  }
}
