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

  @Column(name = "fee_type")
  @Enumerated(EnumType.STRING)
  private FeeType feeType = FeeType.없음;

  @Column(name = "truncate_type")
  @Enumerated(EnumType.STRING)
  private TruncateType truncateType = TruncateType.NO;

  @Column(name = "rate")
  private double rate = 0.01f;

  @Column(name = "fee_amount")
  private long amount = 0L;

  public static LedgerFeePolicyDetail newInstance(
      FeeType feeType, TruncateType truncateType, double rate, long amount) {

    if (feeType == null) {
      throw new IllegalArgumentException("feeType cannot be null");
    }

    if (truncateType == null) {
      throw new IllegalArgumentException("truncateType cannot be null");
    }
    return new LedgerFeePolicyDetail(feeType, truncateType, rate, amount);
  }

  public LedgerFeeDetail calculate(LedgerDetail ledgerDetail) {
    return LedgerFeeDetail.newInstance(
        this, feeType.calculate(ledgerDetail.getAmount(), amount, rate, truncateType));
  }
}
