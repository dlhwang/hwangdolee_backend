package com.dollee.bank.deposit.service;

import com.dollee.bank.account.domain.command.DepositCommand;
import com.dollee.bank.account.domain.service.DepositCommandService;
import com.dollee.bank.common.redis.DistributedLock;
import com.dollee.bank.deposit.dto.DepositMapper;
import com.dollee.bank.deposit.dto.DepositRequest.DepositSave;
import com.dollee.bank.deposit.dto.DepositResponse.DepositVO;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositService {

  private final DepositCommandService domainService;

  @DistributedLock(key = "'bank:account' + #save.getAccountNumber()")
  public DepositVO deposit(DepositSave save) {
    return DepositMapper.toResponse(
        domainService.process(
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
