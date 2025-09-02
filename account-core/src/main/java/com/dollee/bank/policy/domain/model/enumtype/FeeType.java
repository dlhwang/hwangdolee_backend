package com.dollee.bank.policy.domain.model.enumtype;

import lombok.Getter;

@Getter
public enum FeeType {
  없음 {
    @Override
    public long calculate(long amount, long feeValue, double rate, TruncateType truncateType) {
      return 0L;
    }
  },
  정액 {
    @Override
    public long calculate(long amount, long feeValue, double rate, TruncateType truncateType) {
      return truncateType.truncate(feeValue);
    }
  },
  정율 {
    @Override
    public long calculate(long amount, long feeValue, double rate, TruncateType truncateType) {
      return truncateType.truncate((long) (amount * rate));
    }
  };

  public abstract long calculate(
      long amount, long feeValue, double rate, TruncateType truncateType);
}
