package com.dollee.bank.policy.infra.entity;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.domain.model.LedgerLimitPolicyDetail;
import com.dollee.bank.policy.domain.model.Policy;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(LedgerLimitPolicyEntity.DISCRIMINATOR)
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerLimitPolicyEntity extends Policy implements Serializable {
  public static final String DISCRIMINATOR = "LIMIT";
  @Serial
  private static final long serialVersionUID = 1941686701889658803L;

  @Embedded
  private LedgerLimitPolicyDetail ledgerLimitPolicyDetail;

  protected LedgerLimitPolicyEntity(
      LedgerType ledgerType,
      LocalDateTime effectiveFrom,
      LedgerLimitPolicyDetail ledgerLimitPolicyDetail) {
    super(ledgerType, effectiveFrom);
    this.ledgerLimitPolicyDetail = ledgerLimitPolicyDetail;
  }

  protected LedgerLimitPolicyEntity(
      String id,
      LedgerType ledgerType,
      LocalDateTime effectiveFrom,
      LedgerLimitPolicyDetail ledgerLimitPolicyDetail) {
    super(id, ledgerType, effectiveFrom);
    this.ledgerLimitPolicyDetail = ledgerLimitPolicyDetail;
  }
}
