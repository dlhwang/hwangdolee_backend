package com.dollee.bank.deposit.controller;

import com.dollee.bank.deposit.dto.DepositRequest;
import com.dollee.bank.deposit.dto.DepositResponse;
import com.dollee.bank.deposit.service.DepositService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "입금 관리 v1", description = "입금 관리 컨트롤러 입니다.")
@RequestMapping("/api/dolleebank/account")
@RequiredArgsConstructor
public class DepositController {

  private final DepositService service;

  @PostMapping
  @Operation(summary = "입금", description = "입금합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  public ResponseEntity<DepositResponse.DepositVO> deposit(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
          required = true,
          description = "입금 정보.")
      @RequestBody
      DepositRequest.DepositSave save) {
    return ResponseEntity.ok(service.deposit(save));
  }
}
