package com.dollee.bank.account.infra.entity;

import com.dollee.bank.account.domain.model.AccountDetail;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;
import com.dollee.bank.common.util.Ulid;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

  @Column(name = "account_id", nullable = false)
  private String accountId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", insertable = false, updatable = false)
  private AccountEntity account;

  @Embedded
  private LedgerDetail ledgerDetail;

  @Embedded
  private AccountDetail accountDetail;

  @Embedded
  private LedgerFeeDetail ledgerFeeDetail;

  protected LedgerEntity(
      AccountEntity account, LedgerDetail ledgerDetail, AccountDetail accountDetail,
      LedgerFeeDetail ledgerFeeDetail) {

    if (account == null) {
      throw new IllegalArgumentException("account는 null일 수 없습니다.");
    }
    if (ledgerDetail == null) {
      throw new IllegalArgumentException("ledgerDetail은 null일 수 없습니다.");
    }
    if (accountDetail == null) {
      throw new IllegalArgumentException("accountDetail은 null일 수 없습니다.");
    }
    if (ledgerFeeDetail == null) {
      throw new IllegalArgumentException("ledgerFeeDetail은 null일 수 없습니다.");
    }

    this.accountId = account.getId();
    this.account = account;
    this.ledgerDetail = ledgerDetail;
    this.accountDetail = accountDetail;
    this.ledgerFeeDetail = ledgerFeeDetail;
  }

  public static LedgerEntity newInstance(
      AccountEntity account, LedgerDetail ledgerDetail, AccountDetail accountDetail,
      LedgerFeeDetail ledgerFeeDetail) {
    return new LedgerEntity(account, ledgerDetail, accountDetail, ledgerFeeDetail);
  }
}
