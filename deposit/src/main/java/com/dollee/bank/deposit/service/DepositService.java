package com.dollee.bank.deposit.service;

import com.dollee.bank.account.domain.command.DepositCommand;
import com.dollee.bank.account.domain.service.LedgerService;
import com.dollee.bank.deposit.dto.DepositMapper;
import com.dollee.bank.deposit.dto.DepositRequest.DepositSave;
import com.dollee.bank.deposit.dto.DepositResponse.DepositVO;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositService {

  private final LedgerService ledgerService;

  public DepositVO deposit(DepositSave save) {
    return DepositMapper.toResponse(
        ledgerService.deposit(
            new DepositCommand(
                save.getAccountNumber(),
                LocalDateTime.now(),
                save.getAmount(),
                save.getDescription(),
                save.getUserId()
            )
        )
    );
  }
}
