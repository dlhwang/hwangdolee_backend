package com.dollee.bank.transfer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class TransferRequest {

  @Schema(description = "이체 요청 정보")
  @Data
  public static class Transfer {

    @Schema(name = "fromAccountNumber", description = "출금할 계좌 번호", example = "88812338212")
    private String fromAccountNumber;
    @Schema(name = "toAccountNumber", description = "입금할 계좌 번호", example = "88812338212")
    private String toAccountNumber;
    @Schema(name = "amount", description = "이체 금액", example = "10000")
    private long amount;
    @Schema(name = "description", description = "비고", example = "비고")
    private String description;
    @Schema(name = "userId", description = "이체 요청 사용자", example = "user")
    private String userId;
  }
}
