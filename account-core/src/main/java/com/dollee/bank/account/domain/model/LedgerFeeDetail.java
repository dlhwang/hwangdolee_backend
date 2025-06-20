package com.dollee.bank.account.domain.model;

import com.dollee.bank.policy.domain.model.LedgerFeePolicyDetail;
import com.dollee.bank.policy.domain.model.enumtype.FeeType;
import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerFeeDetail {

  @Embedded
  private LedgerFeePolicyDetail ledgerFeePolicyDetail;

  @Column(name = "fee", nullable = false)
  private long fee;

  public static LedgerFeeDetail newInstance(LedgerFeePolicyDetail ledgerFeePolicyDetail, long fee) {
    return new LedgerFeeDetail(ledgerFeePolicyDetail, fee);
  }

  public FeeType getFeeType() {
    return ledgerFeePolicyDetail.getFeeType();
  }

  public TruncateType getTruncateType() {
    return ledgerFeePolicyDetail.getTruncateType();
  }

  public double getRate() {
    return ledgerFeePolicyDetail.getRate();
  }

  public long getAmount() {
    return ledgerFeePolicyDetail.getAmount();
  }
}
