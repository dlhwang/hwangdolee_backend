package com.dollee.bank.account.infra.entity;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.common.util.Money;

public class AccountEntityMapper {
  public static AccountEntity toEntity(Account domain) {
    return new AccountEntity(
        domain.getAccountId(),
        domain.getAccountNumberToString(),
        domain.getAmount(),
        domain.getUserId()
    );
  }

  public static AccountEntity toEntityForSave(Account domain) {
    return new AccountEntity(
        domain.getAccountNumberToString(),
        domain.getAmount(),
        domain.getUserId()
    );
  }

  public static Account toDomain(AccountEntity entity) {
    return Account.of(
        entity.getId(),
        AccountNumber.to(entity.getAccountNumber()),
        Money.wons(entity.getBalance()),
        entity.getUserId()
    );
  }
}
