/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.util.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig
{
    @Pointcut("execution(* com.rahrana.restapi.controller.AuthenticationController.*(..))")
    public void controllerLayerExecution(){}

    @Pointcut("execution(* com.rahrana.restapi.services.*.*(..)))")
    public void serviceLayerExecution(){}

    @Pointcut("execution(* com.rahrana.restapi.repo.*.*(..))")
    public void repoLayerExecution(){}
}
