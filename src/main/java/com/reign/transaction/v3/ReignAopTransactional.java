package com.reign.transaction.v3;

import com.reign.transaction.v1.TransactionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * @ClassName: ReignAopTransactional
 * @Description: 自定义事务注解实现
 * @Author: wuwx
 * @Date: 2021-01-15 15:56
 **/

@Aspect
@Component
@Scope("prototype")   //这里要用多例，防止出现线程安全问题
public class ReignAopTransactional {

    @Autowired
    private ReignTransactionUtil transactionUtil;

    @Around("execution(* com.reign.demo.service.*.*(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        //1.获取代理对象的方法并判断该方法上是否加上注解
        ReignTransactional transactional = getMethodTransaction(pjp);
        //2.如果存在事务注解，开启事务

        if (transactional != null) {
            System.out.println("添加了事务注解---------------------");
            transactionUtil.begin();
            //代理调用方法，注意：如果调用方法抛出异常不会执行后续代码
            pjp.proceed();
            //调用方法之后执行
            transactionUtil.commit();
        } else {
            pjp.proceed();
        }
    }



    /**
     * 拦截异常
     */
    @AfterThrowing("execution(* com.reign.demo.service.*.*(..))")
    public void afterThrowing() {
        transactionUtil.rollback();
    }


    public ReignTransactional getMethodTransaction(ProceedingJoinPoint pjp) {
        //获取方法名称
        String methodName = pjp.getSignature().getName();
        //获取目标对象
        Class<?> classTarget = pjp.getTarget().getClass();
        //获取目标对象类型
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method objMethod = null;
        try {
            objMethod = classTarget.getMethod(methodName, par);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        ReignTransactional transactional = objMethod.getAnnotation(ReignTransactional.class);
        return transactional;
    }

}
