package com.dollee.bank.account.dto;

import com.dollee.bank.account.domain.model.Account;
import com.dollee.bank.account.domain.model.AccountDetail;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;
import com.dollee.bank.account.dto.AccountResponse.AccountVO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class AccountMapper {

  public static AccountResponse.AccountSave toResponse(Account account) {
    return AccountResponse.AccountSave.of(account.getAccountId(), account.getAccountNumberToString(), account.getAmount());
  }

  public static AccountResponse.AccountVO toResponse(Ledger ledger) {
    return AccountResponse.AccountVO.of(toResponse(ledger.getDetail()),
        toResponse(ledger.getAccountDetail()), toResponse(ledger.getLedgerFeeDetail()));
  }

  public static List<AccountVO> toResponse(List<Ledger> ledgers) {
    return ledgers.stream().map(AccountMapper::toResponse).toList();
  }

  public static AccountResponse.LedgerDetailVO toResponse(LedgerDetail detail) {
    return new AccountResponse.LedgerDetailVO(detail.getLedgerType(), detail.getOccurredAt(),
        detail.getAmount(), detail.getDescription(), detail.getExecutedBy()
    );
  }

  public static AccountResponse.AccountDetailVO toResponse(AccountDetail detail) {
    return new AccountResponse.AccountDetailVO(detail.getAccountNumberToString(),
        detail.getBalance());
  }

  public static AccountResponse.FeeDetailVO toResponse(LedgerFeeDetail detail) {
    return new AccountResponse.FeeDetailVO(
        detail.getFeeType(),
        detail.getTruncateType(),
        detail.getRate(),
        detail.getAmount(),
        detail.getFee());
  }
}
