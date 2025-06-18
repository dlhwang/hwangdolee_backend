package com.dollee.bank.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataResponse<T> extends ErrorResponse {
  T result;
}
