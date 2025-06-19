package com.dollee.bank.account.domain.service;

import com.dollee.bank.account.domain.repository.LedgerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LedgerService {

  private final LedgerRepository repository;

}
