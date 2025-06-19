package com.dollee.bank.policy.infra.entity;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.domain.model.DefaultLedgerFeePolicyRegistry;
import com.dollee.bank.policy.domain.model.LedgerFeePolicyDetail;
import com.dollee.bank.policy.domain.model.Policy;
import com.dollee.bank.policy.domain.model.enumtype.FeeType;
import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
import jakarta.persistence.Column;
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
@DiscriminatorValue(LedgerFeePolicyEntity.DISCRIMINATOR)
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerFeePolicyEntity extends Policy implements Serializable {

  public static final String DISCRIMINATOR = "FEE";
  @Serial
  private static final long serialVersionUID = 1941686701889658803L;

  @Embedded
  private LedgerFeePolicyDetail ledgerFeePolicyDetail;

  protected LedgerFeePolicyEntity(LedgerType ledgerType, LocalDateTime effectiveFrom,
      LedgerFeePolicyDetail ledgerFeePolicyDetail) {
    super(ledgerType, effectiveFrom);
    this.ledgerFeePolicyDetail = ledgerFeePolicyDetail;
  }

  protected LedgerFeePolicyEntity(String id, LedgerType ledgerType, LocalDateTime effectiveFrom,
      LedgerFeePolicyDetail ledgerFeePolicyDetail) {
    super(id, ledgerType, effectiveFrom);
    this.ledgerFeePolicyDetail = ledgerFeePolicyDetail;
  }
}
