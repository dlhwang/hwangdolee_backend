package com.dollee.bank.account.infra.repository;

import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.account.infra.entity.AccountEntity;
import com.dollee.bank.account.infra.entity.LedgerEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerJpaRepository extends JpaRepository<LedgerEntity, String> {

  @Query("""
        SELECT COALESCE(SUM(l.ledgerDetail.amount), 0)
        FROM LedgerEntity l
        WHERE l.accountId = :accountId
        AND l.ledgerDetail.ledgerType = :type
        AND l.ledgerDetail.occurredAt BETWEEN :start AND :end
      """)
  long sumLedgerAmountBetween(
      @Param("accountId") String accountId,
      @Param("type") LedgerType type,
      @Param("start") LocalDateTime start,
      @Param("end") LocalDateTime end);

  List<LedgerEntity> findAllByAccountOrderByLedgerDetail_OccurredAtDesc(AccountEntity account);
}
