package com.dollee.bank.account.domain.repository;

import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.infra.repository.LedgerJpaRepository;
import com.dollee.bank.common.enumtype.Cycle;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LedgerRepositoryImpl implements LedgerRepository {

  private final LedgerJpaRepository repository;

  @Override
  public Ledger save(Ledger save) {
    return null;
  }

  @Override
  public Ledger findById(String id) {
    return null;
  }

  @Override
  public long getSumByCycle(String accountId, Cycle cycle) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime start, end;

    switch (cycle) {
      case DAILY -> {
        start = now.toLocalDate().atStartOfDay();
        end = start.plusDays(1);
      }
      case WEEKLY -> {
        start = now.with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
        end = start.plusDays(7);
      }
      case MONTHLY -> {
        start = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
        end = start.plusMonths(1);
      }
      default -> throw new IllegalArgumentException("Unknown cycle");
    }

    return repository.sumLedgerAmountBetween(accountId, start, end);
  }
}
