package com.dollee.bank.policy.infra.repository;

import com.dollee.bank.policy.infra.entity.LedgerLimitPolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerLimitPolicyJpaRepository extends JpaRepository<LedgerLimitPolicyEntity, String> {

}
