package com.dollee.bank.deposit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "com.dollee.bank.account",
        "com.dollee.bank.deposit",
        "com.dollee.bank.policy",
        "com.dollee.bank.common",
        "com.dollee.bank.config"
    })
public class DepositApplication {

  public static void main(String[] args) {
    try {
      SpringApplication.run(DepositApplication.class, args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
