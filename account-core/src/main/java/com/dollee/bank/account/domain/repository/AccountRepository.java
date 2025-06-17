package com.dollee.bank.account.domain.repository;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountNumber;
import java.util.List;
import org.springframework.data.domain.Page;

public interface AccountRepository {
  Account save(Account save);
  Account findById(String accountId);
  Account findByAccountNumber(String accountNumber);
  void delete(String accountId);
  boolean existsByAccountNumber(AccountNumber candidate);
}
