/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.util.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect @Configuration @Slf4j public class LoggingAspect
{

    @Before(value = "com.rahrana.restapi.util.aspects.CommonJoinPointConfig.controllerLayerExecution()")
    public void beforeController(JoinPoint joinPoint)
    {
        log.debug("before execution of {}", joinPoint);
    }

    @Before(value = "com.rahrana.restapi.util.aspects.CommonJoinPointConfig.serviceLayerExecution()")
    public void before(JoinPoint joinPoint)
    {
        log.debug("before execution of {}", joinPoint);
    }

    @AfterReturning(value = "com.rahrana.restapi.util.aspects.CommonJoinPointConfig.repoLayerExecution()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result)
    {
        log.debug("{} returned with value {}", joinPoint, result);
    }

    @After(value = "com.rahrana.restapi.util.aspects.CommonJoinPointConfig.serviceLayerExecution()")
    public void after(JoinPoint joinPoint)
    {
        log.debug("after execution of {}", joinPoint);
    }
}
