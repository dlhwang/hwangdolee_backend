package com.dollee.bank.account.domain.repository;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.account.domain.model.enumtype.LedgerType;

public interface AccountRepository {
  Account save(Account save);

  Account findById(String accountId);

  Account findByAccountNumberAndUserId(LedgerType ledgerType, String accountNumber, String userId);

  void delete(String accountId);

  boolean existsByAccountNumber(AccountNumber candidate);
}
