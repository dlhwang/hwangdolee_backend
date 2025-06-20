package com.dollee.bank.account.domain.model;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerDetail {

  @Enumerated(EnumType.STRING)
  private LedgerType ledgerType;

  private LocalDateTime occurredAt;
  private long amount;
  private String description; // 송금인/수취인 정보가 필요한 경우 추가 필드도 고려

  @Column(name = "executed_by", nullable = false)
  private String executedBy;

  public static LedgerDetail newInstance(
      LedgerType ledgerType,
      LocalDateTime occurredAt,
      long amount,
      String description,
      String executedBy) {
    return new LedgerDetail(ledgerType, occurredAt, amount, description, executedBy);
  }
}
