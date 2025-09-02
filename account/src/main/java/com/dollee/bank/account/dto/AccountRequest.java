package com.dollee.bank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class AccountRequest {
  @Data
  @Schema(description = "계좌 검색 조건")
  public static class AccountSearch {

  }

  @Data
  @Schema(description = "계좌 등록")
  public static class AccountSave {
    String userId;
    long balance;
  }
}
