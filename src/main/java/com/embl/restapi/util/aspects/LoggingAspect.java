package com.embl.restapi.util.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class LoggingAspect {

    @Before("com.embl.restapi.util.aspects.CommonJoinPointConfig.repoLayerExecution()")
    public void logMethodCall(JoinPoint jp)
    {
        String methodName = jp.getSignature().getName();
        log.debug("Before " + jp);
    }

    @AfterReturning(value = "com.embl.restapi.util.aspects.CommonJoinPointConfig.repoLayerExecution()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result)
    {
        log.debug("{} returned with value {}", joinPoint, result);
    }

    @Before(value = "com.embl.restapi.util.aspects.CommonJoinPointConfig.serviceLayerExecution()")
    public void before(JoinPoint joinPoint)
    {
        log.debug("before execution of {}", joinPoint);
    }

    @After(value = "com.embl.restapi.util.aspects.CommonJoinPointConfig.serviceLayerExecution()")
    public void after(JoinPoint joinPoint)
    {
        log.debug("after execution of {}", joinPoint);
    }
}
