package com.dollee.bank.common.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingAspect {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Around("@annotation(com.dollee.bank.common.logging.Loggable)")
  public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String className = signature.getDeclaringTypeName();
    String methodName = signature.getName();
    Object[] args = joinPoint.getArgs();
    Object result = null;
    try {
      log.info("▶️ Method called: {}.{}({})", className, methodName, getJson(args));
      result = joinPoint.proceed();
      log.info("✅ Method returned: {}", getJson(result));
    } catch (Throwable t) {
      log.error("❌ Exception in {}.{}: {}", className, methodName, t.getMessage(), t);
      throw t;
    }

    return result;
  }

  private String getJson(Object[] args) {
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    // JSON으로 인자 직렬화
    String argumentsJson;
    try {
      argumentsJson = objectMapper.writeValueAsString(args);
    } catch (JsonProcessingException e) {
      argumentsJson = Arrays.toString(args); // fallback
    }
    return argumentsJson;
  }

  private String getJson(Object args) {
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    try {
      return objectMapper.writeValueAsString(args);
    } catch (JsonProcessingException e) {
      log.warn("Failed to serialize object to JSON", e);
      return String.valueOf(args);
    }
  }
}
