package com.dollee.bank.account.domain.repository;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.account.infra.entity.AccountEntity;
import com.dollee.bank.account.infra.entity.AccountEntityMapper;
import com.dollee.bank.account.infra.repository.AccountJpaRepository;
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

  //@TODO NEED GLOBAL EXCEPTION AccountNotFoundException
  @Override
  public Account findById(String accountId) {
    return AccountEntityMapper.toDomain(
        jpaRepository.findById(accountId).orElseThrow());
  }

  //@TODO NEED GLOBAL EXCEPTION AccountNotFoundException
  @Override
  public Account findByAccountNumber(String accountNumber) {
    return AccountEntityMapper.toDomain(
        jpaRepository.findByAccountNumber(accountNumber).orElseThrow());
  }

  //@TODO NEED GLOBAL EXCEPTION AccountNotFoundException
  @Override
  public void delete(String accountId) {
    AccountEntity accountEntity = jpaRepository.findById(accountId).orElseThrow();
    jpaRepository.delete(accountEntity);
  }

  @Override
  public boolean existsByAccountNumber(AccountNumber candidate) {
    return jpaRepository.existsByAccountNumber(candidate.getValue());
  }
}
