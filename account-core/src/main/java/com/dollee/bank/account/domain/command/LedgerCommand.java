package com.dollee.bank.account.domain.command;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public abstract class LedgerCommand {

  protected String accountNumber;
  protected LedgerType ledgerType;
  protected LocalDateTime occurredAt;
  protected long amount;
  protected String description;
  protected String executedBy;

  protected LedgerCommand(String accountNumber, LedgerType ledgerType, LocalDateTime occurredAt,
      long amount, String description, String executedBy) {

    if (!StringUtils.hasText(accountNumber)) {
      throw new IllegalArgumentException("accountNumber cannot be null");
    }
    if (ledgerType == null) {
      throw new IllegalArgumentException("ledgerType cannot be null");
    }
    if (amount < 0) {
      throw new IllegalArgumentException("금액은 0원 초과이여야 합니다.");
    }

    if (!StringUtils.hasText(executedBy)) {
      throw new IllegalArgumentException("입출금을 시도하는 인원이 필요합니다.");
    }

    if (occurredAt == null) {
      this.occurredAt = LocalDateTime.now();
    }

    this.accountNumber = accountNumber;
    this.ledgerType = ledgerType;
    this.occurredAt = occurredAt;
    this.amount = amount;
    this.description = description;
    this.executedBy = executedBy;
  }
}
