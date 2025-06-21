package com.dollee.bank.withdraw.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class WithdrawRequest {

  @Schema(description = "출금 요청 정보")
  @Data
  public static class Withdraw {

    @Schema(name = "accountNumber", description = "입금할 계좌 번호", example = "88812338212")
    private String accountNumber;
    @Schema(name = "amount", description = "입금 금액", example = "10000")
    private long amount;
    @Schema(name = "description", description = "비고", example = "비고")
    private String description;
    @Schema(name = "userId", description = "입금 요청 사용자", example = "user")
    private String userId;
  }
}
