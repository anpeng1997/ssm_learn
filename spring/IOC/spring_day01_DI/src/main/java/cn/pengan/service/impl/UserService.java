package cn.pengan.service.impl;

import cn.pengan.service.IUserService;

import java.util.Date;

public class UserService implements IUserService {
    private String name;
    private Integer age;
    private Date birthday;

    public UserService(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void print() {
        System.out.println("name:" + name + ",age:" + age + ",birthday:" + birthday);
    }

    public int addUser(String name) {
        System.out.println("userService中的userDao:" + name);
        return 1;
    }
}
