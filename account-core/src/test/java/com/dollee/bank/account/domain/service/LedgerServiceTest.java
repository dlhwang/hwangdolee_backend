package com.dollee.bank.account.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dollee.bank.account.domain.command.DepositCommand;
import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountDetail;
import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;
import com.dollee.bank.account.domain.repository.AccountRepository;
import com.dollee.bank.account.domain.repository.LedgerRepository;
import com.dollee.bank.common.enumtype.Cycle;
import com.dollee.bank.common.util.Money;
import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import com.dollee.bank.policy.domain.model.LedgerFeePolicyDetail;
import com.dollee.bank.policy.domain.model.LedgerLimitPolicy;
import com.dollee.bank.policy.domain.model.enumtype.FeeType;
import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
import com.dollee.bank.policy.domain.service.LedgerFeePolicyService;
import com.dollee.bank.policy.domain.service.LedgerLimitPolicyService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LedgerServiceTest {

  @InjectMocks
  private LedgerService ledgerService;

  @Mock
  private LedgerFeePolicyService feePolicyService;

  @Mock
  private LedgerLimitPolicyService limitPolicyService;

  @Mock
  private AccountRepository accountRepository;

  @Mock
  private LedgerRepository ledgerRepository;


  @DisplayName("입금시 정상 입금된다")
  @Test
  void deposit() {
    // given
    DepositCommand command = new DepositCommand(
        "123-456",
        LocalDateTime.now(),
        100_000L,
        "입금 설명",
        "system"
    );

    LedgerFeePolicy feePolicy = mock(LedgerFeePolicy.class);
    LedgerLimitPolicy limitPolicy = mock(LedgerLimitPolicy.class);

    Account account = mock(Account.class);
    Ledger expectedLedger = mock(Ledger.class);

    when(feePolicyService.getActivePolicyOrDefault(any(), any())).thenReturn(feePolicy);
    when(limitPolicyService.getActivePolicyOrDefault(any(), any())).thenReturn(limitPolicy);
    when(accountRepository.findByAccountNumber("123-456")).thenReturn(account);

    when(limitPolicy.getCycle()).thenReturn(Cycle.DAILY);
    when(limitPolicy.getAmount()).thenReturn(1_000_000L);

    when(account.getAccountId()).thenReturn("account-id");
    when(account.getAccountDetail()).thenReturn(
        AccountDetail.newInstance(AccountNumber.to("홍길동"), 0, "system"));

    when(feePolicy.calculate(any())).thenReturn(
        LedgerFeeDetail.newInstance(LedgerFeePolicyDetail.newInstance(
            FeeType.없음, TruncateType.NO, 0f, 0L), 0));

    when(ledgerRepository.getSumByCycle(any(), any())).thenReturn(0L);
    when(ledgerRepository.save(any(), account)).thenReturn(expectedLedger);

    // when
    Ledger result = ledgerService.deposit(command);

    // then
    assertThat(result).isEqualTo(expectedLedger);
    verify(account).increaseBalance(eq(Money.wons(100_000L)));
  }

  @Test
  void withdrawal() {
  }

  @Test
  void transfer() {
  }
}