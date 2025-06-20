package com.dollee.bank.policy.domain.model;

import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;
import com.dollee.bank.policy.domain.model.enumtype.FeeType;
import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
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
public class LedgerFeePolicyDetail {

  @Column(name = "fee_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private FeeType feeType;

  @Column(name = "truncate_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private TruncateType truncateType;

  @Column(name = "rate", nullable = false)
  private double rate = 0.01f;

  @Column(name = "fee_amount", nullable = false)
  private long amount = 0L;

  public static LedgerFeePolicyDetail newInstance(
      FeeType feeType, TruncateType truncateType, double rate, long amount) {
    return new LedgerFeePolicyDetail(feeType, truncateType, rate, amount);
  }

  public LedgerFeeDetail calculate(LedgerDetail ledgerDetail) {
    return LedgerFeeDetail.newInstance(
        this, feeType.calculate(ledgerDetail.getAmount(), amount, rate, truncateType));
  }
}
