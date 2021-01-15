package com.reign.demo.domain;

/**
 * @ClassName User
 * @Description TODO
 * @Author wuwenxiang
 * @Date 2021-01-14 21:27
 * @Version 1.0
 **/
public class User {

    private int age;

    private String name;

    private int id;

    public User(int id, String name, int age) {
        this.age = age;
        this.name = name;
        this.id = id;
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
