package com.dollee.bank.account.domain.command;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class TransferCommand {

  private final String fromAccountNumber;
  private final String toAccountNumber;
  private final LocalDateTime occurredAt;
  private final long amount;
  private final String description;
  private final String executedBy;
  public TransferCommand(String fromAccountNumber, String toAccountNumber, LocalDateTime occurredAt,
      long amount, String description, String executedBy) {
    if (!StringUtils.hasText(fromAccountNumber)) {
      throw new IllegalArgumentException("fromAccountNumber cannot be null");
    }
    if (!StringUtils.hasText(toAccountNumber)) {
      throw new IllegalArgumentException("toAccountNumber cannot be null");
    }
    if (amount < 0) {
      throw new IllegalArgumentException("금액은 0원 초과이여야 합니다.");
    }

    if (!StringUtils.hasText(executedBy)) {
      throw new IllegalArgumentException("입출금을 시도하는 인원이 필요합니다.");
    }

    this.fromAccountNumber = fromAccountNumber;
    this.toAccountNumber = toAccountNumber;
    this.occurredAt = occurredAt == null ? LocalDateTime.now() : occurredAt;
    this.amount = amount;
    this.description = description;
    this.executedBy = executedBy;
  }
}
