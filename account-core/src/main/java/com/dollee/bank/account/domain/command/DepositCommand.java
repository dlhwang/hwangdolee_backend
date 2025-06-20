package com.dollee.bank.account.domain.command;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DepositCommand {

  private final String accountNumber;
  private final LedgerType ledgerType = LedgerType.DEPOSIT;
  private final LocalDateTime occurredAt;
  private final long amount;
  private final String description;
  private final String executedBy;

  public DepositCommand(String accountNumber, LocalDateTime occurredAt, long amount,
      String description,
      String executedBy) {
    this.accountNumber = accountNumber;
    this.occurredAt = occurredAt;
    this.amount = amount;
    this.description = description;
    this.executedBy = executedBy;
  }
}
