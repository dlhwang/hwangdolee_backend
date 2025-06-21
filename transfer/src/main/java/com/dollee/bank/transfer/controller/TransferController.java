package com.dollee.bank.transfer.controller;

import com.dollee.bank.transfer.dto.TransferRequest;
import com.dollee.bank.transfer.dto.TransferResponse;
import com.dollee.bank.transfer.service.TransferService;
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
@Tag(name = "이체 관리 v1", description = "이체 컨트롤러 입니다.")
@RequestMapping("/api/dolleebank/transfer")
@RequiredArgsConstructor
public class TransferController {

  private final TransferService service;

  @PostMapping
  @Operation(summary = "이체", description = "이체합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad Request"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  public ResponseEntity<TransferResponse> post(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
          required = true,
          description = "이체 정보.")
      @RequestBody
      TransferRequest.Transfer save) {
    return ResponseEntity.ok(service.save(save));
  }
}
