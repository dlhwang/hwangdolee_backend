package com.dollee.bank.common.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * https://helloworld.kurly.com/blog/distributed-redisson-lock/
 */
@Component
public class AopForTransaction {

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
    return joinPoint.proceed();
  }
}
