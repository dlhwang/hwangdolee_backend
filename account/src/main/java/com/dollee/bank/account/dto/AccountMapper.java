package com.dollee.bank.account.dto;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.dto.AccountResponse.AccountVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class AccountMapper {

  public static AccountResponse.AccountVO toResponse(Account account) {
    return AccountResponse.AccountVO.of(
        account.getAccountId(),
        account.getAccountNumberToString(),
        account.getAmount()
    );
  }

  public static AccountResponse toResponse(Page<Account> accounts) {
    return AccountResponse.to(
        new PageImpl<>(
            accounts.map(AccountMapper::toResponse).toList(),
            accounts.getPageable(),
            accounts.getTotalElements())
    );
  }

}
