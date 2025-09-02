package com.dollee.bank.policy.infra.repository;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.infra.entity.LedgerFeePolicyEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerFeePolicyJpaRepository extends JpaRepository<LedgerFeePolicyEntity, String> {

  Optional<LedgerFeePolicyEntity>
  findFirstByLedgerTypeAndEffectiveFromGreaterThanEqualOrderByEffectiveFromDesc(
      LedgerType ledgerType, LocalDateTime now);
}
