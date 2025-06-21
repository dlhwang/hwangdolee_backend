package com.dollee.bank.account.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dollee.bank.account.domain.command.DepositCommand;
import com.dollee.bank.account.domain.command.TransferCommand;
import com.dollee.bank.account.domain.command.WithdrawCommand;
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
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LedgerServiceTest {

  @InjectMocks
  private DepositCommandService depositCommandService;
  @InjectMocks
  private WithdrawCommandService withdrawCommandService;
  @InjectMocks
  private TransferCommandService transferCommandService;

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
    when(accountRepository.findByAccountNumberAndUserId(command.getLedgerType(), "123-456",
        command.getExecutedBy())).thenReturn(account);

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
    Ledger result = depositCommandService.process(command);

    // then
    assertThat(result).isEqualTo(expectedLedger);
    verify(account).increaseBalance(eq(Money.wons(100_000L)));
  }

  @DisplayName("출금시 정상 출금된다")
  @Test
  void withdrawal() {
    // given
    WithdrawCommand command = new WithdrawCommand(
        "123-456",
        LocalDateTime.now(),
        50_000L,
        "출금 설명",
        "system"
    );

    LedgerFeePolicy feePolicy = mock(LedgerFeePolicy.class);
    LedgerLimitPolicy limitPolicy = mock(LedgerLimitPolicy.class);
    Account account = mock(Account.class);
    Ledger expectedLedger = mock(Ledger.class);

    when(feePolicyService.getActivePolicyOrDefault(any(), any())).thenReturn(feePolicy);
    when(limitPolicyService.getActivePolicyOrDefault(any(), any())).thenReturn(limitPolicy);
    when(accountRepository.findByAccountNumberAndUserId(command.getLedgerType(), "123-456",
        command.getExecutedBy())).thenReturn(account);

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
    Ledger result = withdrawCommandService.process(command);

    // then
    assertThat(result).isEqualTo(expectedLedger);
    verify(account).decreaseBalance(eq(Money.wons(50_000L)));
  }


  @DisplayName("이체시 출금과 입금이 순차적으로 수행된다")
  @Test
  void transfer() {
    // given
    TransferCommand command = new TransferCommand(
        "from-acc",
        "to-acc",
        LocalDateTime.now(),
        30_000L,
        "이체 설명",
        "system"
    );

    WithdrawCommand withdrawCommand = command.getWithdraw();
    DepositCommand depositCommand = command.getDeposit();

    Ledger withdrawLedger = mock(Ledger.class);
    Ledger depositLedger = mock(Ledger.class);

    // 출금 서비스 Mock
    when(withdrawCommandService.process(withdrawCommand)).thenReturn(withdrawLedger);

    // 입금 서비스 Mock
    when(depositCommandService.process(depositCommand)).thenReturn(depositLedger);

    // when
    List<Ledger> result = transferCommandService.process(command);

    // then
    assertThat(result).containsExactly(withdrawLedger, depositLedger);

    verify(withdrawCommandService).process(withdrawCommand);
    verify(depositCommandService).process(depositCommand);
  }
}