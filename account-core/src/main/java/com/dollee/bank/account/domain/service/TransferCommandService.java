package com.dollee.bank.account.domain.service;

import com.dollee.bank.account.domain.command.TransferCommand;
import com.dollee.bank.account.domain.model.Ledger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TransferCommandService {

  private final DepositCommandService depositService;
  private final WithdrawCommandService withdrawCommandService;

  @Transactional
  public List<Ledger> process(TransferCommand command) {
    Ledger withdraw = withdrawCommandService.process(command.getWithdraw());
    Ledger deposit = depositService.process(command.getDeposit());
    return List.of(deposit, withdraw);
  }
}
