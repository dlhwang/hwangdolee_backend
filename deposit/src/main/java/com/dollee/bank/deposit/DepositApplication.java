package com.dollee.bank.deposit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ={"com.dollee.bank.deposit", "com.dollee.bank.common"})
public class DepositApplication {
  public static void main(String[] args) {
    try {
      SpringApplication.run(DepositApplication.class, args);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
