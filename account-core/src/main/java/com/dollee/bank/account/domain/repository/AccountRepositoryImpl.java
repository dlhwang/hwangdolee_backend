package com.dollee.bank.account.domain.repository;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.account.infra.entity.AccountEntity;
import com.dollee.bank.account.infra.entity.AccountEntityMapper;
import com.dollee.bank.account.infra.repository.AccountJpaRepository;
import com.dollee.bank.common.exception.DataNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

  private final AccountJpaRepository jpaRepository;

  @Override
  public Account save(Account save) {
    return AccountEntityMapper.toDomain(
        jpaRepository.save(AccountEntityMapper.toEntityForSave(save)));
  }

  @Override
  public Account findById(String accountId) {
    return AccountEntityMapper.toDomain(
        jpaRepository
            .findById(accountId)
            .orElseThrow(() -> new DataNotFoundException("존재하지 않은 계좌입니다.")));
  }

  @Override
  public Account findByAccountNumberAndUserId(LedgerType ledgerType, String accountNumber,
      String userId) {
    Optional<AccountEntity> entityOpt = switch (ledgerType) {
      case DEPOSIT -> jpaRepository.findByAccountNumber(AccountNumber.to(accountNumber));
      case WITHDRAWAL ->
          jpaRepository.findByAccountNumberAndUserId(AccountNumber.to(accountNumber), userId);
      default -> throw new IllegalStateException("Unexpected ledger type: " + ledgerType);
    };

    return AccountEntityMapper.toDomain(
        entityOpt.orElseThrow(() -> new DataNotFoundException("존재하지 않은 계좌입니다."))
    );
  }

  @Override
  public void delete(String accountId) {
    AccountEntity accountEntity =
        jpaRepository
            .findById(accountId)
            .orElseThrow(() -> new DataNotFoundException("존재하지 않은 계좌입니다."));
    jpaRepository.delete(accountEntity);
  }

  @Override
  public boolean existsByAccountNumber(AccountNumber candidate) {
    return jpaRepository.existsByAccountNumber(candidate);
  }
}
