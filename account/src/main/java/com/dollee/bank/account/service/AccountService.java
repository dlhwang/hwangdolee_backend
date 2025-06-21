package com.dollee.bank.account.service;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.account.domain.repository.AccountRepository;
import com.dollee.bank.account.domain.repository.LedgerRepository;
import com.dollee.bank.account.domain.service.AccountNumberGenerator;
import com.dollee.bank.account.dto.AccountMapper;
import com.dollee.bank.account.dto.AccountRequest.AccountSave;
import com.dollee.bank.account.dto.AccountResponse;
import com.dollee.bank.account.dto.AccountResponse.AccountVO;
import com.dollee.bank.common.enumtype.BankCode;
import com.dollee.bank.common.logging.Loggable;
import com.dollee.bank.common.util.Money;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

  private final AccountRepository accountRepository;
  private final LedgerRepository ledgerRepository;
  private final AccountNumberGenerator accountNumberGenerator;

  @Loggable
  public List<AccountVO> getAccountDetails(String accountNumber) {
    return AccountMapper.toResponse(ledgerRepository.getLedgersByAccountNumber(accountNumber));
  }

  @Loggable
  public AccountResponse.AccountSave save(AccountSave save) {
    AccountNumber accountNumber = generateUniqueAccountNumber(BankCode.DOLLEE);
    return AccountMapper.toResponse(
        accountRepository.save(
            Account.newInstance(accountNumber, Money.wons(save.getBalance()), save.getUserId())));
  }

  @Loggable
  public void remove(String accountId) {
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
