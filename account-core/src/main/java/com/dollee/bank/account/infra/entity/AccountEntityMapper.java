package com.dollee.bank.account.infra.entity;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.common.util.Money;
import java.util.List;

public class AccountEntityMapper {

    /**
     * Convert a domain Account to a persistence AccountEntity including its id.
     *
     * The returned AccountEntity contains the domain's account id, account number
     * (as a String), balance, and user id — suitable for updating or persisting
     * an existing record.
     *
     * @param domain domain Account to convert
     * @return a new AccountEntity populated from the domain object
     */
    public static AccountEntity toEntity(Account domain) {
        return new AccountEntity(
            domain.getAccountId(),
            domain.getAccountNumberToString(),
            domain.getAmount(),
            domain.getUserId()
        );
    }

    /**
     * Convert a domain Account into an AccountEntity for insertion (no id set).
     *
     * <p>The returned entity contains the account number (as a string), balance, and userId
     * from the domain object and is intended for use when saving a new record.</p>
     *
     * @param domain the domain Account to convert
     * @return a new AccountEntity populated for save (without an id)
     */
    public static AccountEntity toEntityForSave(Account domain) {
        return new AccountEntity(
            domain.getAccountNumberToString(),
            domain.getAmount(),
            domain.getUserId()
        );
    }

    /**
     * Convert a persistence AccountEntity into its domain Account representation.
     *
     * Creates a domain Account using the entity's id, account number (converted via
     * AccountNumber.to), balance (wrapped with Money.wons), and userId.
     *
     * @param entity the persistence entity to convert
     * @return the corresponding domain Account
     */
    public static Account toDomain(AccountEntity entity) {
        return Account.of(
            entity.getId(),
            AccountNumber.to(entity.getAccountNumber()),
            Money.wons(entity.getBalance()),
            entity.getUserId()
        );
    }

}
