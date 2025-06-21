package com.dollee.bank.account.domain.command;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TransferCommand {

  private final WithdrawCommand withdraw;
  private final DepositCommand deposit;

  public TransferCommand(String fromAccountNumber, String toAccountNumber, LocalDateTime occurredAt,
      long amount, String description, String executedBy) {
    withdraw = new WithdrawCommand(fromAccountNumber, occurredAt, amount, description, executedBy);
    deposit = new DepositCommand(toAccountNumber, occurredAt, amount, description, executedBy);
  }
}
