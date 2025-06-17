package com.dollee.bank.account.infra.repository;

import com.dollee.bank.account.infra.entity.AccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, String> {
 boolean existsByAccountNumber(String accountNumber);
 Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
