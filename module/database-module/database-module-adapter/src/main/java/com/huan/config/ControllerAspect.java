package com.huan.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAspect {
    @Pointcut(value = "execution(* com.huan.web..*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("前置通知");
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        System.out.println("后置通知");
    }

    @AfterReturning(pointcut="pointcut()",returning = "result")
    public void result(JoinPoint joinPoint,Object result){
        System.out.println("返回通知:"+result);
    }

}
