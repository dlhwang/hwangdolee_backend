package com.dollee.bank.deposit.dto;

import com.dollee.bank.policy.domain.model.enumtype.FeeType;
import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Value;

public class DepositResponse {

  @Value
  public static class DepositVO {

    DepositDetailVO detail;
    AccountDetailVO accountDetail;
    FeeDetailVO feeDetail;

    public static DepositVO of(DepositDetailVO detail, AccountDetailVO accountDetail,
        FeeDetailVO feeDetail) {
      return new DepositVO(detail, accountDetail, feeDetail);
    }
  }

  @Value
  @Schema(description = "입금 상세 정보")
  public static class DepositDetailVO {

    @Schema(name = "occurredAt", description = "입금일시", example = "입금일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime occurredAt;
    @Schema(name = "amount", description = "입금 금액", example = "10000")
    long amount;
    @Schema(name = "description", description = "비고", example = "비고")
    String description;
    @Schema(name = "executedBy", description = "입금한 사용자 ID", example = "dk10202j202")
    String executedBy;
  }

  @Value
  @Schema(description = "계좌 정보")
  public static class AccountDetailVO {

    @Schema(name = "accountNumber", description = "계좌번호", example = "88812338212")
    String accountNumber;
    @Schema(name = "balance", description = "잔고", example = "1000000")
    long balance;
  }

  @Value
  @Schema(description = "수수료 정보")
  public static class FeeDetailVO {

    @Schema(name = "feeType", description = "수수료 부과 방법", example = "[없음, 정액, 정율]")
    FeeType feeType;
    @Schema(name = "truncateType", description = "절사방법", example = "[NO, ONE, TEN]")
    TruncateType truncateType;
    @Schema(name = "rate", description = "수수료 부과 방법이 정율일시 반영 비율", example = "0.01")
    double rate;
    @Schema(name = "amount", description = "수수료 부과 방법이 정액일시 반영 금액", example = "1000")
    long amount;
    @Schema(name = "fee", description = "수수료", example = "500")
    long fee;
  }

}
