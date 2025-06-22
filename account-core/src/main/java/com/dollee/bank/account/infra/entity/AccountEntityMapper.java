package com.dollee.bank.account.infra.entity;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.common.util.Money;
import java.util.List;

public class AccountEntityMapper {
  public static AccountEntity toEntity(Account domain) {
    return new AccountEntity(
        domain.getAccountId(),
        domain.getAccountDetail().getAccountNumber(),
        domain.getAmount(),
        domain.getUserId());
  }

  public static List<AccountEntity> toEntity(List<Account> domain) {
    return domain.stream().map(AccountEntityMapper::toEntity).toList();
  }

  public static AccountEntity toEntityForSave(Account domain) {
    return new AccountEntity(domain.getAccountNumber(), domain.getAmount(), domain.getUserId());
  }

  public static Account toDomain(AccountEntity entity) {
    return Account.of(
        entity.getId(),
        entity.getAccountNumber(),
        Money.wons(entity.getBalance()),
        entity.getUserId());
  }

  public static List<Account> toDomain(List<AccountEntity> entity) {
    return entity.stream().map(AccountEntityMapper::toDomain).toList();
  }
}
