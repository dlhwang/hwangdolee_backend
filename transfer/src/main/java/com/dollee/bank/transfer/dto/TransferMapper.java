package com.dollee.bank.transfer.dto;

import com.dollee.bank.account.domain.model.AccountDetail;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;

public class TransferMapper {

  public static TransferResponse.TransferVO toResponse(Ledger ledger) {
    return TransferResponse.TransferVO.of(toResponse(ledger.getDetail()),
        toResponse(ledger.getAccountDetail()), toResponse(ledger.getLedgerFeeDetail()));
  }

  public static TransferResponse.TransferDetailVO toResponse(LedgerDetail detail) {
    return new TransferResponse.TransferDetailVO(detail.getLedgerType(), detail.getOccurredAt(),
        detail.getAmount(), detail.getDescription(), detail.getExecutedBy());
  }

  public static TransferResponse.AccountDetailVO toResponse(AccountDetail detail) {
    return new TransferResponse.AccountDetailVO(detail.getAccountNumberToString(),
        detail.getBalance());
  }

  public static TransferResponse.FeeDetailVO toResponse(LedgerFeeDetail detail) {
    return new TransferResponse.FeeDetailVO(
        detail.getFeeType(),
        detail.getTruncateType(),
        detail.getRate(),
        detail.getAmount(),
        detail.getFee());
  }
}
