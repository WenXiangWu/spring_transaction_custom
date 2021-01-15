package com.reign;

import com.reign.demo.domain.User;
import com.reign.demo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author wuwenxiang
 * @Date 2021-01-14 21:41
 * @Version 1.0
 **/
public class Test1 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = context.getBean(UserService.class);
        User user = new User(2,"老王",33);
        //userService.add(user);
        //userService.addV2(user);
        userService.addV3(user);

    }
}
