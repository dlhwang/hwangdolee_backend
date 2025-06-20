package com.dollee.bank.account.domain.repository;

import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.common.enumtype.Cycle;

public interface LedgerRepository {
  Ledger save(Ledger save);

  Ledger findById(String id);

  long getSumByCycle(String accountId, Cycle cycle);
}
