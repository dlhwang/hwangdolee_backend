package com.dollee.bank.policy.domain.model;

import com.dollee.bank.common.enumtype.Cycle;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerLimitPolicyDetail {

  @Column(name = "cycle")
  @Enumerated(EnumType.STRING)
  private Cycle cycle = Cycle.NONE;

  @Column(name = "limit_amount")
  private long amount = 0L;

  public static LedgerLimitPolicyDetail newInstance(Cycle cycle, long amount) {
    if (cycle == null) {
      throw new IllegalArgumentException("cycle cannot be null");
    }

    return new LedgerLimitPolicyDetail(cycle, amount);
  }
}
