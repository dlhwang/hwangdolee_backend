package com.dollee.bank.account.domain.model;

import com.dollee.bank.common.util.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {

  private String accountId;
  private AccountDetail accountDetail;

  public static Account newInstance(AccountNumber accountNumber, Money balance, String userId) {
    return new Account(accountNumber, balance, userId);
  }

  public static Account of(String accountId, AccountNumber accountNumber, Money balance,
      String userId) {
    return new Account(accountId, AccountDetail.newInstance(accountNumber, balance.longValue(), userId));
  }

  protected Account(AccountNumber accountNumber, Money balance, String userId) {
    this.accountDetail = AccountDetail.newInstance(accountNumber, balance.longValue(), userId);
  }

  public void increaseBalance(Money amount) {
    this.accountDetail = AccountDetail.increaseBalance(accountDetail, amount);
  }

  public void decreaseBalance(Money amount) {
    this.accountDetail = AccountDetail.decreaseBalance(accountDetail, amount);
  }

  public long getAmount() {
    return accountDetail.getAmount();
  }

  public AccountNumber getAccountNumber() {
    return accountDetail.getAccountNumber();
  }

  public String getUserId(){
    return this.accountDetail.getUserId();
  }

  public String getAccountNumberToString() {
    return accountDetail.getAccountNumberToString();
  }
}
