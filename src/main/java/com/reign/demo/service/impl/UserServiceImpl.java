package com.reign.demo.service.impl;

import com.reign.demo.dao.UserDao;
import com.reign.demo.domain.User;
import com.reign.demo.service.UserService;
import com.reign.transaction.v1.TransactionUtil;
import com.reign.transaction.v3.ReignTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author wuwenxiang
 * @Date 2021-01-14 21:26
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionUtil transactionUtil;


    /**
     * 版本1：使用编程式手动提交
     *
     * @param user
     */
    @Override
    public void add(User user) {
        //1.开启事务
        TransactionStatus status = null;
        try {
            status = transactionUtil.begin();
            System.out.println("事务-------开启");

            userDao.add(user);
            //System.out.println("添加用户："+user.toString());
            //if (user.getId() != 1) throw new IllegalArgumentException("参数id错了");
            User user1 = new User(32, "第二天");
            userDao.add(user1);

            //提交事务
            transactionUtil.commit(status);
            System.out.println("事务--------提交");
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            transactionUtil.rollback(status);
            System.out.println("事务--------回滚");
        }

    }

    /**
     * 版本2：使用aop
     */
    public void addV2(User user) {
        userDao.add(user);
        int i = 1 / 0;
        User user1 = new User(32, "第二天");
        userDao.add(user1);
    }


    /**
     * 版本3：使用自定义的事务注解
     * @param user
     */
    @ReignTransactional
    @Override
    public void addV3(User user) {
        userDao.add(user);
        int i = 1 / 0;
        User user1 = new User(100, "annotation");
        userDao.add(user1);
    }
}
