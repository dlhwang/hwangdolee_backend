package com.dollee.bank.policy.infra.repository;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.infra.entity.LedgerLimitPolicyEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerLimitPolicyJpaRepository extends
    JpaRepository<LedgerLimitPolicyEntity, String> {

  Optional<LedgerLimitPolicyEntity> findFirstByLedgerTypeAndEffectiveFromLessThanEqualOrderByEffectiveFrom(
      LedgerType ledgerType, LocalDateTime baseTime);
}
