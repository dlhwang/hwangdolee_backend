package com.dollee.bank.account.domain.command;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WithdrawCommand extends LedgerCommand {

  public WithdrawCommand(String accountNumber, LocalDateTime occurredAt, long amount,
      String description,
      String executedBy) {
    super(accountNumber, LedgerType.WITHDRAWAL, occurredAt, amount, description, executedBy);
  }
}
