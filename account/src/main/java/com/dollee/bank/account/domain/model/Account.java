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
    private Money balance;

    public static Account newInstance(String accountId, Money balance){
        return new Account(accountId, balance);
    }

    public void increaseBalance(Money amount) {
        this.balance = this.balance.plus(amount);
    }

    public void decreaseBalance(Money amount) {
        this.balance = this.balance.minus(amount);
    }
}
