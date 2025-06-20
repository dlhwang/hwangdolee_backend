package com.dollee.bank.policy.domain.repository;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.common.exception.DataNotFoundException;
import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import com.dollee.bank.policy.infra.entity.LedgerFeePolicyEntity;
import com.dollee.bank.policy.infra.entity.PolicyEntityMapper;
import com.dollee.bank.policy.infra.repository.LedgerFeePolicyJpaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LedgerFeePolicyRepositoryImpl implements LedgerFeePolicyRepository {

  private final LedgerFeePolicyJpaRepository repository;

  @Override
  public LedgerFeePolicy save(LedgerFeePolicy save) {
    return PolicyEntityMapper.toDomain(
        repository.save(PolicyEntityMapper.toEntityForSave(save)));
  }

  @Override
  public LedgerFeePolicy findById(String policyId) {
    return PolicyEntityMapper.toDomain(
        repository.findById(policyId)
            .orElseThrow(() -> new DataNotFoundException("존재하지 않은 정책입니다.")));
  }

  @Override
  public void delete(String policyId) {
    LedgerFeePolicyEntity entity = repository.findById(policyId)
        .orElseThrow(() -> new DataNotFoundException("존재하지 않은 정책입니다."));
    repository.delete(entity);
  }

  @Override
  public LedgerFeePolicy getActivePolicyOrDefault(LedgerType ledgerType, LocalDateTime baseTime) {
    return repository.findFirstByLedgerTypeAndEffectiveFromGreaterThanEqualOrderByEffectiveFromDesc(
            ledgerType, baseTime).map(PolicyEntityMapper::toDomain)
        .orElseGet(() -> this.save(LedgerFeePolicy.createDefault(ledgerType, baseTime)));
  }
}
