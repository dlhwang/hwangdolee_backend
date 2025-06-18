package com.dollee.bank.account.infra.entity;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountDetail;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;
import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.common.util.Ulid;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "ledger")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerEntity {

  @Id
  @Ulid
  @Column(name = "ledger_id", columnDefinition = "varchar(500)", nullable = false)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false)
  private AccountEntity account;

  @Embedded
  private LedgerDetail ledgerDetail;

  @Embedded
  private AccountDetail accountDetail;

  @Embedded
  private LedgerFeeDetail ledgerFeeDetail;

  protected LedgerEntity(AccountEntity account, LedgerDetail ledgerDetail, AccountDetail accountDetail) {
    this.account = account;
    this.ledgerDetail = ledgerDetail;
    this.accountDetail = accountDetail;
  }

  public static LedgerEntity newInstance(AccountEntity account, LedgerDetail ledgerDetail, AccountDetail accountDetail) {
    return new LedgerEntity(account, ledgerDetail, accountDetail);
  }

}
