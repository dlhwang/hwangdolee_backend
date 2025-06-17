package com.dollee.bank.account.domain.service;

import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.common.enumtype.BankCode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("random")
public class RandomAccountNumberGenerator implements AccountNumberGenerator{
  private static final Random RANDOM = new Random();

  @Override
  public AccountNumber generate(BankCode bankCode) {
    String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
    int randomNum = RANDOM.nextInt(900_000) + 100_000; // 100000 ~ 999999
    String raw = bankCode.getCode() + date + randomNum;
    return AccountNumber.to(raw);
  }
}
