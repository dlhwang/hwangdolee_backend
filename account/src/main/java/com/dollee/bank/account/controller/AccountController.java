package com.dollee.bank.account.controller;

import com.dollee.bank.account.dto.AccountRequest;
import com.dollee.bank.account.dto.AccountResponse;
import com.dollee.bank.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "계좌 관리 v1", description = "계좌 관리 컨트롤러 입니다.")
@RequestMapping("/api/dolleebank/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService service;

  @GetMapping("/{accountId}")
  @Operation(summary = "계좌 상세 조회", description = "계좌 하나를 조회합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  public ResponseEntity<AccountResponse.AccountVO> getAccount(
      @Parameter(description = "계좌의 아이디") @PathVariable String accountId) {
    return ResponseEntity.ok(service.getAccount(accountId));
  }

  @PostMapping
  @Operation(summary = "계좌 등록", description = "계좌을 등록합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  public ResponseEntity<AccountResponse.AccountVO> post(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
          required = true,
          description = "계좌 등록 정보.")
      @RequestBody
      AccountRequest.AccountSave save) {
    return ResponseEntity.ok(service.save(save));
  }

  @DeleteMapping("/{accountId}")
  @Operation(summary = "계좌 삭제", description = "계좌 하나를 삭제합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  public ResponseEntity<Void> remove(
      @Parameter(description = "계좌의 아이디") @PathVariable String accountId) {
    service.remove(accountId);
    return ResponseEntity.noContent().build();
  }
}
