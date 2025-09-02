package com.dollee.bank.withdraw.controller;

import com.dollee.bank.withdraw.dto.WithdrawRequest;
import com.dollee.bank.withdraw.dto.WithdrawResponse;
import com.dollee.bank.withdraw.service.WithdrawService;
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
@Tag(name = "인출 관리 v1", description = "인출 관리 컨트롤러 입니다.")
@RequestMapping("/api/dolleebank/withdraw")
@RequiredArgsConstructor
public class WithdrawController {

  private final WithdrawService service;

  @PostMapping
  @Operation(summary = "인출", description = "인출을 합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  public ResponseEntity<WithdrawResponse.WithdrawVO> post(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
          required = true,
          description = "인출 정보")
      @RequestBody
      WithdrawRequest.Withdraw save) {
    return ResponseEntity.ok(service.save(save));
  }
}
