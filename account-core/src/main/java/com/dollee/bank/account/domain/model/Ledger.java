package com.dollee.bank.account.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Ledger {

  private String ledgerId;
  private String accountId;
  private LedgerDetail detail;
  private AccountDetail accountDetail;
  private LedgerFeeDetail ledgerFeeDetail;

  protected Ledger(String accountId, LedgerDetail ledgerDetail, AccountDetail accountDetail,
      LedgerFeeDetail ledgerFeeDetail) {
    this.accountId = accountId;
    this.detail = ledgerDetail;
    this.accountDetail = accountDetail;
    this.ledgerFeeDetail = ledgerFeeDetail;
  }

  public static Ledger of(String id, String accountId, LedgerDetail ledgerDetail,
      AccountDetail accountDetail, LedgerFeeDetail ledgerFeeDetail) {
    return new Ledger(id, accountId, ledgerDetail, accountDetail, ledgerFeeDetail);
  }

  public static Ledger newInstance(String accountId, LedgerDetail ledgerDetail,
      AccountDetail accountDetail, LedgerFeeDetail ledgerFeeDetail) {
    return new Ledger(accountId, ledgerDetail, accountDetail, ledgerFeeDetail);
  }
}
