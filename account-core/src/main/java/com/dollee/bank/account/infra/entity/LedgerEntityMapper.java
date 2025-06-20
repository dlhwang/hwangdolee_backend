package com.dollee.bank.account.infra.entity;

import com.dollee.bank.account.domain.model.AccountDetail;
import com.dollee.bank.account.domain.model.Ledger;
import com.dollee.bank.account.domain.model.LedgerDetail;
import com.dollee.bank.account.domain.model.LedgerFeeDetail;

public class LedgerEntityMapper {

  public static LedgerEntity toEntity(Ledger domain, AccountEntity accountEntity) {
    return new LedgerEntity(
        domain.getLedgerId(),
        accountEntity.getId(),
        accountEntity,
        domain.getDetail(),
        domain.getAccountDetail(),
        domain.getLedgerFeeDetail()
    );
  }

  public static LedgerEntity toEntityForSave(AccountEntity accountEntity, LedgerDetail ledgerDetail,
      AccountDetail accountDetail, LedgerFeeDetail ledgerFeeDetail) {
    return LedgerEntity.newInstance(
        accountEntity,
        ledgerDetail,
        accountDetail,
        ledgerFeeDetail
    );
  }

  public static Ledger toDomain(LedgerEntity entity) {
    return Ledger.of(
        entity.getId(),
        entity.getAccountId(),
        entity.getLedgerDetail(),
        entity.getAccountDetail(),
        entity.getLedgerFeeDetail()
    );
  }
}
