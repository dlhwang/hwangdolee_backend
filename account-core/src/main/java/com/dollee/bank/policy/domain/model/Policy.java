package com.dollee.bank.policy.domain.model;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.common.util.Ulid;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "policy_type")
@Table(name = "policy")
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Policy implements Serializable {

  @Serial
  private static final long serialVersionUID = 8798822264817869236L;

  @Id
  @Ulid
  @Column(name = "policy_id", columnDefinition = "varchar(500)", nullable = false)
  private String id;

  @Column(name = "policy_type", nullable = false, insertable = false, updatable = false)
  protected String policy_type;

  @Enumerated(EnumType.STRING)
  @Column(name = "ledger_type", nullable = false)
  private LedgerType ledgerType;

  @Column(name = "effective_from", nullable = false)
  private LocalDateTime effectiveFrom;

  protected Policy(LedgerType ledgerType, LocalDateTime effectiveFrom) {
    this.ledgerType = ledgerType;
    this.effectiveFrom = effectiveFrom;
  }

  protected Policy(String id, LedgerType ledgerType, LocalDateTime effectiveFrom) {
    this.id = id;
    this.ledgerType = ledgerType;
    this.effectiveFrom = effectiveFrom;
  }
}
