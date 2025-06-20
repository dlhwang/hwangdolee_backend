package com.dollee.bank.deposit.dto;

import lombok.Data;

public class DepositRequest {

  @Data
  public static class DepositSave {

    private String accountNumber;
    private long amount;
    private String description;
    private String userId;
  }

}
