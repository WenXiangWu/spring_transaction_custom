package com.reign.transaction.v3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @ClassName: ReignTransactionUtil
 * @Description: 事务工具类，必须要多例，否则会导致线程安全问题
 * @Author: wuwx
 * @Date: 2021-01-15 16:27
 **/
@Component
@Scope("prototype")
public class ReignTransactionUtil {

    //获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    private TransactionStatus transactionStatus = null;

    /**
     * 开启事务
     */
    public TransactionStatus begin() {
        System.out.println("开启事务");
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }


    /**
     * 提交事务
     */
    public void commit() {
        System.out.println("提交事务");
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        System.out.println("回滚事务");
        dataSourceTransactionManager.rollback(transactionStatus);
    }


}
