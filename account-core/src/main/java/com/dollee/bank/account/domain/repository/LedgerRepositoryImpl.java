package com.dollee.bank.account.domain.repository;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.account.infra.entity.AccountEntityMapper;
import com.dollee.bank.account.infra.entity.LedgerEntityMapper;
import com.dollee.bank.account.infra.repository.LedgerJpaRepository;
import com.dollee.bank.common.enumtype.Cycle;
import com.dollee.bank.common.exception.DataNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LedgerRepositoryImpl implements LedgerRepository {

  private final LedgerJpaRepository repository;

  @Override
  public Ledger save(Ledger save, Account account) {
    return LedgerEntityMapper.toDomain(repository.save(
        LedgerEntityMapper.toEntityForSave(
            AccountEntityMapper.toEntity(account),
            save.getDetail(),
            save.getAccountDetail(),
            save.getLedgerFeeDetail()
        )
    ));
  }

  @Override
  public Ledger findById(String id) {
    return LedgerEntityMapper.toDomain(
        repository.findById(id).orElseThrow(() -> new DataNotFoundException("해당 이력을 찾을 수 없습니다.")));
  }

  @Override
  public long getSumByCycle(String accountId, Cycle cycle, LedgerType ledgerType) {
    if (cycle == Cycle.NONE) {
      return 0L;
    }

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

    return Optional.of(repository.sumLedgerAmountBetween(accountId, ledgerType, start, end))
        .orElse(0L);
  }
}
