package com.dollee.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages ={"com.dollee.bank.account", "com.dollee.bank.common"})
public class AccountApplication {
  public static void main(String[] args) {
    try {
      SpringApplication.run(AccountApplication.class, args);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
