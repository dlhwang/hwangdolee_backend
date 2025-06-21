package com.dollee.bank.transfer.service;

import com.dollee.bank.account.domain.command.TransferCommand;
import com.dollee.bank.account.domain.service.TransferCommandService;
import com.dollee.bank.common.redis.DistributedLock;
import com.dollee.bank.transfer.dto.TransferMapper;
import com.dollee.bank.transfer.dto.TransferRequest;
import com.dollee.bank.transfer.dto.TransferResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransferService {

  private final TransferCommandService transferService;

  @DistributedLock(key = "'bank:account' + #save.getFromAccountNumberx()")
  public TransferResponse save(TransferRequest.Transfer save) {
    return new TransferResponse(transferService.process(
        new TransferCommand(
            save.getFromAccountNumber(),
            save.getToAccountNumber(),
            LocalDateTime.now(),
            save.getAmount(),
            save.getDescription(),
            save.getUserId()
        )
    ).stream().map(TransferMapper::toResponse).toList());
  }
}
