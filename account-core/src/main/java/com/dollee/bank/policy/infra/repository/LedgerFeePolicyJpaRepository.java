package com.dollee.bank.policy.infra.repository;

import com.dollee.bank.account.infra.entity.AccountEntity;
import com.dollee.bank.policy.domain.model.LedgerFeePolicy;
import com.dollee.bank.policy.infra.entity.LedgerFeePolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerFeePolicyJpaRepository extends JpaRepository<LedgerFeePolicyEntity, String> {

}
