package com.dollee.bank.common.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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

  private String getJson(Object... args){
    // JSON으로 인자 직렬화
    String argumentsJson;
    try {
      argumentsJson = objectMapper.writeValueAsString(args);
    } catch (Exception e) {
      e.printStackTrace();
      argumentsJson = Arrays.toString(args); // fallback
    }
    return argumentsJson;
  }
}
