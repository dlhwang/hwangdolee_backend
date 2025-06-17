package com.dollee.bank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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
  public static class AccountVO {

    @Schema(description = "계좌 아이디", example = "hbavArRhRwOfQ5swS7eAq")
    private String accountId;

    @Schema(description = "계좌 아이디", example = "123-1234-123")
    private String accountNumber;

    @Schema(description = "계좌 잔고")
    private Long amount;

    public static AccountVO of(String accountId, String accountNumber, Long amount) {
      return AccountVO.builder()
          .accountId(accountId)
          .accountNumber(accountNumber)
          .amount(amount)
          .build();
    }
  }

  public static AccountResponse to(Page<AccountVO> vos) {
    return AccountResponse.builder()
        .accountList(vos)
        .build();
  }
}
