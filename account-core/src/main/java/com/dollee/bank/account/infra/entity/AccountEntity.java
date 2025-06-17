package com.dollee.bank.account.infra.entity;

import com.dollee.bank.common.util.Ulid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "account")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity {
    @Id
    @Ulid
    @Column(name = "account_id", columnDefinition = "varchar(500)", nullable = false)
    private String id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private long balance = 0;

    @Column(name = "user_id", nullable = false)
    private String userId;

    protected AccountEntity(String accountNumber, long balance, String userId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public static AccountEntity newInstance(String accountNumber, long balance, String userId){
        return new AccountEntity(accountNumber, balance, userId);
    }

}
