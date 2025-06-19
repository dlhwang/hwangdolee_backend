package com.dollee.bank.account.domain.repository;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.Ledger;

public interface LedgerRepository {
  Ledger save(Ledger save);
  Ledger findById(String id);
}
