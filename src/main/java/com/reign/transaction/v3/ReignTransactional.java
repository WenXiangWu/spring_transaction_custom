package com.reign.transaction.v3;

import java.lang.annotation.*;

/**
 * @ClassName ReignTransactional
 * @Description 采用AOP封装事务管理
 * @Author wuwenxiang
 * @Date 2021-01-14 22:36
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface ReignTransactional {
}
