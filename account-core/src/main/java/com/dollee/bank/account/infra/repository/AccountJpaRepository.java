package com.dollee.bank.account.infra.repository;

import com.dollee.bank.account.domain.model.AccountNumber;
import com.dollee.bank.account.infra.entity.AccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, String> {

  boolean existsByAccountNumber(AccountNumber accountNumber);

  Optional<AccountEntity> findByAccountNumber(AccountNumber accountNumber);

  Optional<AccountEntity> findByAccountNumberAndUserId(AccountNumber accountNumber, String userId);
}
