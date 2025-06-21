package com.dollee.bank.withdraw.dto;

import com.dollee.bank.account.domain.model.AccountDetail;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;

public class WithdrawMapper {

  public static WithdrawResponse.WithdrawVO toResponse(Ledger deposit) {
    return WithdrawResponse.WithdrawVO.of(toResponse(deposit.getDetail()),
        toResponse(deposit.getAccountDetail()), toResponse(deposit.getLedgerFeeDetail()));
  }

  public static WithdrawResponse.WithdrawDetailVO toResponse(LedgerDetail detail) {
    return new WithdrawResponse.WithdrawDetailVO(
        detail.getOccurredAt(), detail.getAmount(), detail.getDescription(), detail.getExecutedBy()
    );
  }

  public static WithdrawResponse.AccountDetailVO toResponse(AccountDetail detail) {
    return new WithdrawResponse.AccountDetailVO(detail.getAccountNumberToString(),
        detail.getBalance());
  }

  public static WithdrawResponse.FeeDetailVO toResponse(LedgerFeeDetail detail) {
    return new WithdrawResponse.FeeDetailVO(
        detail.getFeeType(),
        detail.getTruncateType(),
        detail.getRate(),
        detail.getAmount(),
        detail.getFee());
  }
}
