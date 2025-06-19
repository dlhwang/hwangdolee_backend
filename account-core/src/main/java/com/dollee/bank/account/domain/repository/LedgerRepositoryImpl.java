package com.dollee.bank.account.domain.repository;

import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.infra.repository.LedgerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LedgerRepositoryImpl implements LedgerRepository{

  private final LedgerJpaRepository repository;

  @Override
  public Ledger save(Ledger save) {
    return null;
  }

  @Override
  public Ledger findById(String id) {
    return null;
  }
}
