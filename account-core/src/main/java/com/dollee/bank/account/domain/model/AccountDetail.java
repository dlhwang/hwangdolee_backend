package com.dollee.bank.account.domain.model;

import com.dollee.bank.common.exception.NotInvalidException;
import com.dollee.bank.common.util.Money;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDetail {

  private AccountNumber accountNumber;
  private long balance;
  private String userId;

  public static AccountDetail newInstance(
      AccountNumber accountNumber, long balance, String userId) {
    return new AccountDetail(accountNumber, balance, userId);
  }

  protected static AccountDetail increaseBalance(AccountDetail accountDetail, Money amount) {
    return new AccountDetail(
        accountDetail.getAccountNumber(),
        Money.wons(accountDetail.getBalance()).plus(amount).longValue(),
        accountDetail.getUserId());
  }

  public long getAmount() {
    return balance;
  }

  public String getAccountNumberToString() {
    return accountNumber.getNumber();
  }

  public boolean canDecrease(Money amount) {
    return Money.wons(this.balance).isGreaterThanOrEqual(amount);
  }

  protected AccountDetail decrease(Money amount) {
    if (!canDecrease(amount)) {
      throw new NotInvalidException("인출할 수 없습니다");
    }
    return new AccountDetail(
        accountNumber,
        Money.wons(balance).minus(amount).longValue(),
        userId);
  }
}
