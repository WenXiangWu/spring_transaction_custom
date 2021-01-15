package com.reign.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName AopLog
 * @Description 切面类
 * @Author wuwenxiang
 * @Date 2021-01-14 21:24
 * @Version 1.0
 **/
@Component
@Aspect
public class AopLog {

    @Before("execution(* com.reign.demo.service.UserService.add(..))")
    public void before(){
        System.out.println("前置通知");
    }

    @After("execution(* com.reign.demo.service.UserService.add(..))")
    public void after(){
        System.out.println("后置通知");
    }

    @AfterReturning("execution(* com.reign.demo.service.UserService.add(..))")
    public void afterReturning(){
        System.out.println("异常通知afterReturning");
    }


    /**
     *
     * TODOspring事务失效与这个有关
     */
    @AfterThrowing("execution(* com.reign.demo.service.UserService.add(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing通知");
    }

    @Around("execution(* com.reign.demo.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
       //调用方法之前执行
        System.out.println("环绕通知   调用方法之前执行");
        proceedingJoinPoint.proceed(); //代理调用方法，注意：如果调用方法抛出异常不会执行后续代码
        //FIXME  使用事务的时候事务方法一定要抛异常，不能够去try catch
        //调用方法之后执行
        System.out.println("环绕通知   调用方法之后执行");
    }
}
