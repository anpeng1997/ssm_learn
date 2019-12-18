package cn.pengan.dao.impl;

import cn.pengan.dao.IUserDao;

public class UserDao implements IUserDao {
    public int addUser(String name) {
        System.out.println("添加了一个user");
        return 1;
    }
}
