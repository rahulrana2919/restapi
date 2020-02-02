/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.util.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig
{
    @Pointcut("execution(* com.embl.restapi.controller.AuthenticationController.*(..))")
    public void controllerLayerExecution(){}

    @Pointcut("execution(* com.embl.restapi.services.*.*(..)))")
    public void serviceLayerExecution(){}

    @Pointcut("execution(* com.embl.restapi.repo.*.*(..))")
    public void repoLayerExecution(){}
}
