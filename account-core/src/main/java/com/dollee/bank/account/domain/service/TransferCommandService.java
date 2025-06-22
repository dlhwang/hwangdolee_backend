package com.dollee.bank.account.domain.service;

import com.dollee.bank.account.domain.command.TransferCommand;
import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;
import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.account.domain.repository.AccountRepository;
import com.dollee.bank.account.domain.repository.LedgerRepository;
import com.dollee.bank.common.enumtype.Cycle;
import com.dollee.bank.common.util.Money;
import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import com.dollee.bank.policy.domain.model.LedgerLimitPolicy;
import com.dollee.bank.policy.domain.service.LedgerFeePolicyService;
import com.dollee.bank.policy.domain.service.LedgerLimitPolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TransferCommandService {

  private final LedgerRepository repository;
  private final AccountRepository accountRepository;
  private final LedgerFeePolicyService feePolicyService;
  private final LedgerLimitPolicyService limitPolicyService;

  @Transactional
  public List<Ledger> process(TransferCommand command) {
    final LedgerFeePolicy activeFeePolicy = feePolicyService.getActivePolicyOrDefault(
        LedgerType.TRANSFER_FROM, command.getOccurredAt());
    final LedgerLimitPolicy activeLimitPolicy = limitPolicyService.getActivePolicyOrDefault(
        LedgerType.TRANSFER_FROM, command.getOccurredAt());

    final Account fromAccount = accountRepository.findByAccountNumberAndUserId(
        LedgerType.WITHDRAWAL,
        command.getFromAccountNumber(), command.getExecutedBy());

    final Account toAccount = accountRepository.findByAccountNumberAndUserId(LedgerType.DEPOSIT,
        command.getToAccountNumber(), command.getExecutedBy());

    validateLimit(LedgerType.TRANSFER_FROM, fromAccount, activeLimitPolicy, command.getAmount());

    fromAccount.transferTo(toAccount, Money.wons(command.getAmount()));

    // 이체 나가는 내역
    LedgerDetail fromLedgerDetail = LedgerDetail.newInstance(LedgerType.TRANSFER_FROM,
        command.getOccurredAt(), command.getAmount(),
        command.getDescription(), command.getExecutedBy());

    LedgerFeeDetail fromFeeDetail = activeFeePolicy.calculate(fromLedgerDetail);

    // 이체 받는 내역
    LedgerDetail toLedgerDetail = LedgerDetail.newInstance(LedgerType.TRANSFER_TO,
        command.getOccurredAt(), command.getAmount(),
        command.getDescription(), command.getExecutedBy());

    LedgerFeeDetail toFeeDetail = activeFeePolicy.calculate(toLedgerDetail);

    accountRepository.saveAll(List.of(fromAccount, toAccount));

    Ledger from = repository.save(Ledger.newInstance(fromAccount.getAccountId(), fromLedgerDetail,
        fromAccount.getAccountDetail(), fromFeeDetail), fromAccount);

    Ledger to = repository.save(Ledger.newInstance(toAccount.getAccountId(), toLedgerDetail,
        toAccount.getAccountDetail(), toFeeDetail), toAccount);

    return List.of(from, to);
  }

  ;

  private void validateLimit(LedgerType type, Account account, LedgerLimitPolicy policy,
      long amount) {
    if (Cycle.NONE == policy.getCycle()) {
      return;
    }

    final long sumByCycle = repository.getSumByCycle(account.getAccountId(), policy.getCycle(),
        type);
    if (sumByCycle + amount > policy.getAmount()) {
      throw new IllegalArgumentException(getLimitExceededMessage(type, policy));
    }
  }

  private String getLimitExceededMessage(LedgerType type, LedgerLimitPolicy policy) {
    return String.format("%s %s 한도를 초과하였습니다.(%,d원)",
        policy.getCycle().getName(), type.getName(), policy.getAmount());
  }

}
