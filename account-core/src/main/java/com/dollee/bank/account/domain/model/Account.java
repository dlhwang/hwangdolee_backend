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
    private AccountNumber accountNumber;
    private Money balance;
    private String userId;

    public static Account newInstance(AccountNumber accountNumber, Money balance, String userId){
        return new Account(accountNumber, balance, userId);
    }

    public static Account of(String accountId, AccountNumber accountNumber, Money balance, String userId){
        return new Account(accountId, accountNumber, balance, userId);
    }

    protected Account(AccountNumber accountNumber, Money balance, String userId) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.userId = userId;
    }

    public void increaseBalance(Money amount) {
        this.balance = this.balance.plus(amount);
    }

    public void decreaseBalance(Money amount) {
        this.balance = this.balance.minus(amount);
    }

    public long getAmount(){
        return balance.getAmount().longValue();
    }

    public String getAccountNumberToString(){
        return accountNumber.getValue();
    }
}
