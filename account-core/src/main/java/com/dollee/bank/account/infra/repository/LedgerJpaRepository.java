package com.dollee.bank.account.infra.repository;

import com.dollee.bank.account.infra.entity.AccountEntity;
import com.dollee.bank.account.infra.entity.LedgerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerJpaRepository extends JpaRepository<LedgerEntity, String> {

}
