package com.dollee.bank.account.infra.repository;

import com.dollee.bank.account.infra.entity.LedgerEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerJpaRepository extends JpaRepository<LedgerEntity, String> {

  @Query("""
        SELECT SUM(l.ledgerDetail.amount)
        FROM LedgerEntity l
        WHERE l.accountId = :accountId AND l.ledgerDetail.occurredAt BETWEEN :start AND :end
      """)
  long sumLedgerAmountBetween(@Param("accountId") String accountId,
      @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
