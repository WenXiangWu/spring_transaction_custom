package com.reign.transaction.v2;

import com.reign.transaction.v1.TransactionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @ClassName AopTransactional
 * @Description 切面类
 * @Author wuwenxiang
 * @Date 2021-01-14 22:38
 * @Version 1.0
 **/
@Component
@Aspect
public class AopTransactional {

    ThreadLocal<TransactionStatus> local = new ThreadLocal<>();

    @Autowired
    private TransactionUtil transactionUtil;

    @Around("execution(* com.reign.demo.service.UserService.addV2(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //调用方法之前执行
        System.out.println("开启事务：环绕通知   调用方法之前执行");
        TransactionStatus transactionStatus = transactionUtil.begin();
        //直接用ThreadLocal存储事务状态
        local.set(transactionStatus);
        proceedingJoinPoint.proceed(); //代理调用方法，注意：如果调用方法抛出异常不会执行后续代码
        //FIXME  使用事务的时候事务方法一定要抛异常，不能够去try catch;
        //TODO 如果报错方法内部catch处理而不是往外面跑，每次确实commit方法不会执行，即不会提交事务，貌似也达到了目的，但是会占用资源，应该是连接的缓存资源？？？？
        //调用方法之后执行
        transactionUtil.commit(transactionStatus);
        System.out.println("提交事务：环绕通知   调用方法之后执行");

    }

    /**
     *
     * TODOspring事务失效与这个有关
     */
    @AfterThrowing("execution(* com.reign.demo.service.UserService.addV2(..))")
    public void afterThrowing(){
        TransactionStatus transactionStatus = local.get();
        transactionUtil.rollback(transactionStatus);
        //获取当前事务并直接回滚 FIXME 这里直接报错了，没有获取到当前事务
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        System.out.println("回滚成功，afterThrowing通知");
    }


}
