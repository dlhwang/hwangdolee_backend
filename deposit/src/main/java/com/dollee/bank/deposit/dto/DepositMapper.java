package com.dollee.bank.deposit.dto;

import com.dollee.bank.account.domain.model.AccountDetail;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;

public class DepositMapper {

  public static DepositResponse.DepositVO toResponse(Ledger deposit) {
    return DepositResponse.DepositVO.of(toResponse(deposit.getDetail()),
        toResponse(deposit.getAccountDetail()), toResponse(deposit.getLedgerFeeDetail()));
  }

  public static DepositResponse.DepositDetailVO toResponse(LedgerDetail detail) {
    return new DepositResponse.DepositDetailVO(
        detail.getOccurredAt(), detail.getAmount(), detail.getDescription(), detail.getExecutedBy()
    );
  }

  public static DepositResponse.AccountDetailVO toResponse(AccountDetail detail) {
    return new DepositResponse.AccountDetailVO(detail.getAccountNumberToString(),
        detail.getBalance());
  }

  public static DepositResponse.FeeDetailVO toResponse(LedgerFeeDetail detail) {
    return new DepositResponse.FeeDetailVO(
        detail.getFeeType(),
        detail.getTruncateType(),
        detail.getRate(),
        detail.getAmount(),
        detail.getFee());
  }
}
