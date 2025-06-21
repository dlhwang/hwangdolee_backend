package com.dollee.bank.withdraw.service;

import com.dollee.bank.account.domain.command.WithdrawCommand;
import com.dollee.bank.account.domain.service.WithdrawCommandService;
import com.dollee.bank.common.redis.DistributedLock;
import com.dollee.bank.withdraw.dto.WithdrawMapper;
import com.dollee.bank.withdraw.dto.WithdrawRequest.Withdraw;
import com.dollee.bank.withdraw.dto.WithdrawResponse.WithdrawVO;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawService {

  private final WithdrawCommandService withdrawCommandService;

  @DistributedLock(key = "'bank:account' + #save.getAccountNumber()")
  public WithdrawVO save(Withdraw save) {
    return WithdrawMapper.toResponse(
        withdrawCommandService.process(
            new WithdrawCommand(
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
