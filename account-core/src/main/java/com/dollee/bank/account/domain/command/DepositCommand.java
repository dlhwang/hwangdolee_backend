package com.dollee.bank.account.domain.command;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DepositCommand extends LedgerCommand {
  public DepositCommand(String accountNumber, LocalDateTime occurredAt, long amount,
      String description,
      String executedBy) {
    super(accountNumber, LedgerType.DEPOSIT, occurredAt, amount, description, executedBy);
  }
}
