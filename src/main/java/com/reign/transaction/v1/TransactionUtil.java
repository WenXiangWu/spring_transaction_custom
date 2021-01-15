package com.reign.transaction.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @ClassName TransactionUtil
 * @Description 手写编程式事务管理；
 * 编程式事务：需要手动处理事务，手动begin，手动commit，手动rollback   TODO  注意：这个类需要是prototype而不能是单例的，否则多个事务会有线程安全问题
 * @Author wuwenxiang
 * @Date 2021-01-14 22:12
 * @Version 1.0
 **/
@Component
public class TransactionUtil {

    //获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 开启事务
     */
    public TransactionStatus begin() {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }


    /**
     * 提交事务
     */
    public void commit(TransactionStatus status) {
        dataSourceTransactionManager.commit(status);
    }

    /**
     * 回滚事务
     */
    public void rollback(TransactionStatus status) {
        dataSourceTransactionManager.rollback(status);
    }

}
