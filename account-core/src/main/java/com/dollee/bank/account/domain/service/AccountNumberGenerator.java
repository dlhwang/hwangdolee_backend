package com.dollee.bank.account.domain.service;

import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.common.enumtype.BankCode;

public interface AccountNumberGenerator {
    AccountNumber generate(BankCode bankCode);
}
