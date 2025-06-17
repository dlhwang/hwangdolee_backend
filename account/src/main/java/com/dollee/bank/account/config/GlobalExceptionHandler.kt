package com.dollee.market.dolleeMarket.config

import com.dollee.market.dolleeMarket.adaptor.ProductController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    val log: Logger = LoggerFactory.getLogger(ProductController::class.java)

    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("handleGenericException", e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse
                .create(e,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unexpected error occurred."
                )
            )
    }
}