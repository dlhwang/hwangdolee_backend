package com.dollee.bank.policy.infra.entity;

import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import com.dollee.bank.policy.domain.model.LedgerLimitPolicy;

public class PolicyEntityMapper {
  public static LedgerFeePolicyEntity toEntity(LedgerFeePolicy domain) {
    return new LedgerFeePolicyEntity(
        domain.getPolicyId(),
        domain.getLedgerType(),
        domain.getEffectiveFrom(),
        domain.getLedgerFeePolicyDetail());
  }

  public static LedgerFeePolicyEntity toEntityForSave(LedgerFeePolicy domain) {
    return new LedgerFeePolicyEntity(
        domain.getLedgerType(), domain.getEffectiveFrom(), domain.getLedgerFeePolicyDetail());
  }

  public static LedgerFeePolicy toDomain(LedgerFeePolicyEntity entity) {
    return LedgerFeePolicy.of(
        entity.getId(),
        entity.getLedgerType(),
        entity.getEffectiveFrom(),
        entity.getLedgerFeePolicyDetail());
  }

  public static LedgerLimitPolicyEntity toEntity(LedgerLimitPolicy domain) {
    return new LedgerLimitPolicyEntity(
        domain.getPolicyId(),
        domain.getLedgerType(),
        domain.getEffectiveFrom(),
        domain.getLedgerLimitPolicyDetail());
  }

  public static LedgerLimitPolicyEntity toEntityForSave(LedgerLimitPolicy domain) {
    return new LedgerLimitPolicyEntity(
        domain.getLedgerType(), domain.getEffectiveFrom(), domain.getLedgerLimitPolicyDetail());
  }

  public static LedgerLimitPolicy toDomain(LedgerLimitPolicyEntity entity) {
    return LedgerLimitPolicy.of(
        entity.getId(),
        entity.getLedgerType(),
        entity.getEffectiveFrom(),
        entity.getLedgerLimitPolicyDetail());
  }
}
