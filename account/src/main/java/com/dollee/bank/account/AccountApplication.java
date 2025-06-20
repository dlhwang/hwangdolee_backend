package com.dollee.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "com.dollee.bank.account",
        "com.dollee.bank.policy",
        "com.dollee.bank.common",
        "com.dollee.bank.config"
    })
public class AccountApplication {

  public static void main(String[] args) {
    try {
      SpringApplication.run(AccountApplication.class, args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
