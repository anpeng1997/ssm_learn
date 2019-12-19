package cn.pengan.service.impl;

import cn.pengan.dao.IUserDao;
import cn.pengan.dao.impl.UserDao;
import cn.pengan.service.IUserService;

public class UserService implements IUserService {
    public int addUser(String name) {
        System.out.println("userService中的userDao:"+name);
        return 1;
    }
}
