package com.dollee.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication {
  public static void main(String[] args) {
    try {
      SpringApplication.run(AccountApplication.class, args);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
