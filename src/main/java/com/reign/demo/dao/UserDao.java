package com.reign.demo.dao;

import com.reign.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author wuwenxiang
 * @Date 2021-01-14 21:54
 * @Version 1.0
 **/
@Repository
public class UserDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(User user){
        String sql = "insert into user(name,age) values(?,?)";
        int updateResult = jdbcTemplate.update(sql,user.getName(),user.getAge());
        System.out.println("SQL结果："+updateResult);
    }


}
