package com.dollee.bank.policy.domain.repository;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.common.exception.DataNotFoundException;
import com.dollee.bank.policy.domain.model.LedgerLimitPolicy;
import com.dollee.bank.policy.infra.entity.LedgerLimitPolicyEntity;
import com.dollee.bank.policy.infra.entity.PolicyEntityMapper;
import com.dollee.bank.policy.infra.repository.LedgerLimitPolicyJpaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LedgerLimitPolicyRepositoryImpl implements LedgerLimitPolicyRepository {

  private final LedgerLimitPolicyJpaRepository repository;

  @Override
  public LedgerLimitPolicy save(LedgerLimitPolicy save) {
    return PolicyEntityMapper.toDomain(repository.save(PolicyEntityMapper.toEntityForSave(save)));
  }

  @Override
  public LedgerLimitPolicy findById(String policyId) {
    return PolicyEntityMapper.toDomain(
        repository
            .findById(policyId)
            .orElseThrow(() -> new DataNotFoundException("존재하지 않은 정책입니다.")));
  }

  @Override
  public void delete(String policyId) {
    LedgerLimitPolicyEntity entity =
        repository
            .findById(policyId)
            .orElseThrow(() -> new DataNotFoundException("존재하지 않은 정책입니다."));
    repository.delete(entity);
  }

  @Override
  public LedgerLimitPolicy getActivePolicyOrDefault(LedgerType ledgerType, LocalDateTime baseTime) {
    return repository
        .findFirstByLedgerTypeAndEffectiveFromLessThanEqualOrderByEffectiveFrom(
            ledgerType, baseTime)
        .map(PolicyEntityMapper::toDomain)
        .orElseGet(() -> this.save(LedgerLimitPolicy.createDefault(ledgerType, baseTime)));
  }
}
