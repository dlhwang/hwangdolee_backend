package com.dollee.bank.account.dto;

import com.dollee.bank.account.domain.model.enumtype.LedgerType;
import com.dollee.bank.policy.domain.model.enumtype.FeeType;
import com.dollee.bank.policy.domain.model.enumtype.TruncateType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.springframework.data.domain.Page;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "계좌 정보")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountResponse {
  private Page<AccountVO> accountList;

  @Schema(description = "계좌 상세 정보")
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Builder(access = AccessLevel.PRIVATE)
  @Getter
  public static class AccountSave {

    @Schema(description = "계좌 아이디", example = "hbavArRhRwOfQ5swS7eAq")
    private String accountId;

    @Schema(description = "계좌 아이디", example = "123-1234-123")
    private String accountNumber;

    @Schema(description = "계좌 잔고")
    private Long amount;

    public static AccountSave of(String accountId, String accountNumber, Long amount) {
      return AccountSave.builder()
          .accountId(accountId)
          .accountNumber(accountNumber)
          .amount(amount)
          .build();
    }
  }

  @Schema(description = "계좌 상세 정보")
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Builder(access = AccessLevel.PRIVATE)
  @Getter
  public static class AccountVO {
    LedgerDetailVO detail;
    AccountDetailVO accountDetail;
    FeeDetailVO feeDetail;

    public static AccountVO of(LedgerDetailVO detail, AccountDetailVO accountDetail, FeeDetailVO feeDetail) {
      return AccountVO.builder()
          .detail(detail)
          .accountDetail(accountDetail)
          .feeDetail(feeDetail)
          .build();
    }
  }

  @Value
  @Schema(description = "계좌 상세 정보")
  public static class LedgerDetailVO {
    @Schema(name = "ledgerType", description = "거래 구분", example = "일시")
    LedgerType ledgerType;
    @Schema(name = "occurredAt", description = "일시", example = "일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime occurredAt;
    @Schema(name = "amount", description = "금액", example = "10000")
    long amount;
    @Schema(name = "description", description = "비고", example = "비고")
    String description;
    @Schema(name = "executedBy", description = "거래 사용자 ID", example = "dk10202j202")
    String executedBy;
  }

  @Value
  @Schema(description = "계좌 정보")
  public static class AccountDetailVO {

    @Schema(name = "accountNumber", description = "계좌번호", example = "88812338212")
    String accountNumber;
    @Schema(name = "balance", description = "잔고", example = "1000000")
    long balance;
  }

  @Value
  @Schema(description = "수수료 정보")
  public static class FeeDetailVO {

    @Schema(name = "feeType", description = "수수료 부과 방법", example = "[없음, 정액, 정율]")
    FeeType feeType;
    @Schema(name = "truncateType", description = "절사방법", example = "[NO, ONE, TEN]")
    TruncateType truncateType;
    @Schema(name = "rate", description = "수수료 부과 방법이 정율일시 반영 비율", example = "0.01")
    double rate;
    @Schema(name = "amount", description = "수수료 부과 방법이 정액일시 반영 금액", example = "1000")
    long amount;
    @Schema(name = "fee", description = "수수료", example = "500")
    long fee;
  }

  public static AccountResponse to(Page<AccountVO> vos) {
    return AccountResponse.builder().accountList(vos).build();
  }
}
