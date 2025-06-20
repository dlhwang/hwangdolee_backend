package com.dollee.bank.withdraw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dollee.bank.withdraw", "com.dollee.bank.common"})
public class WithdrawApplication {
  public static void main(String[] args) {
    try {
      SpringApplication.run(WithdrawApplication.class, args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
