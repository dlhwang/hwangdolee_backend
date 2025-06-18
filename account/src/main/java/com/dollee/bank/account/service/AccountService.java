package com.dollee.bank.account.service;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.account.domain.repository.AccountRepository;
import com.dollee.bank.account.domain.service.AccountNumberGenerator;
import com.dollee.bank.account.dto.AccountMapper;
import com.dollee.bank.account.dto.AccountRequest.AccountSave;
import com.dollee.bank.account.dto.AccountRequest.AccountSearch;
import com.dollee.bank.account.dto.AccountResponse;
import com.dollee.bank.account.dto.AccountResponse.AccountVO;
import com.dollee.bank.common.enumtype.BankCode;
import com.dollee.bank.common.util.Money;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

  private final AccountRepository accountRepository;
  private final AccountNumberGenerator accountNumberGenerator;

  public AccountVO getAccount(String accountId) {
    log.info("Account getAccount: accountId={}", accountId);
    return AccountMapper.toResponse(accountRepository.findById(accountId));
  }

  public AccountVO save(AccountSave save) {
    AccountNumber accountNumber = generateUniqueAccountNumber(BankCode.DOLLEE);
    log.info("Account save: accountNumber={}, balance={} userId={}", accountNumber.getValue(),
        save.getBalance(), save.getUserId());
    return AccountMapper.toResponse(accountRepository.save(
        Account.newInstance(accountNumber, Money.wons(save.getBalance()), save.getUserId())));
  }

  public void remove(String accountId) {
    log.info("Account remove: accountId={}", accountId);
    accountRepository.delete(accountId);
  }

  private AccountNumber generateUniqueAccountNumber(BankCode bankCode) {
    for (int i = 0; i < 5; i++) {
      AccountNumber candidate = accountNumberGenerator.generate(bankCode);
      if (!accountRepository.existsByAccountNumber(candidate)) {
        return candidate;
      }
    }
    throw new RuntimeException("계좌번호 생성 실패: 중복된 번호가 계속 생성됩니다.");
  }
}
