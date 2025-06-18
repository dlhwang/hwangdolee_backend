package com.dollee.bank.common.util;

import com.dollee.bank.common.enumtype.ErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ErrorResponse {
  private String code;
  private String message;
  private String path;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "Asia/Seoul")
  private LocalDateTime timestamp;

  public static ErrorResponse to(final ErrorCode code) {
    return new ErrorResponse(null, code.getMessage(), null, LocalDateTime.now());
  }

  public static ErrorResponse to(final String message) {
    return new ErrorResponse(null, message, null, LocalDateTime.now());
  }

  public static ErrorResponse of(final String code, final String message, final String path) {
    return new ErrorResponse(code, message, path, LocalDateTime.now());
  }

  public static void error(ServletResponse response, ErrorCode code) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse.setCharacterEncoding("UTF-8");
    httpServletResponse.setStatus(code.getStatus().value());
    httpServletResponse
        .getWriter()
        .write(Objects.requireNonNull(objectMapper.writeValueAsString(ErrorResponse.to(code))));
  }

  public static void token(ServletResponse response, String token) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse.setCharacterEncoding("UTF-8");
    httpServletResponse.setStatus(HttpStatus.OK.value());
    httpServletResponse
        .getWriter()
        .write(Objects.requireNonNull(objectMapper.writeValueAsString(new DataResponse<>(token))));
  }
}
