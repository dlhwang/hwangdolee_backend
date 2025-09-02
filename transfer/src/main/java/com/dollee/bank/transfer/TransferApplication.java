package com.dollee.bank.transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "com.dollee.bank.account",
        "com.dollee.bank.transfer",
        "com.dollee.bank.policy",
        "com.dollee.bank.common",
        "com.dollee.bank.config"
    })
public class TransferApplication {

  public static void main(String[] args) {
    try {
      SpringApplication.run(TransferApplication.class, args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
