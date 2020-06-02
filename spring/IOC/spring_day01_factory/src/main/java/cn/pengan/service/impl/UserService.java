package cn.pengan.service.impl;

import cn.pengan.dao.IUserDao;
import cn.pengan.dao.impl.UserDao;
import cn.pengan.factory.BeanFactory;
import cn.pengan.service.IUserService;

public class UserService implements IUserService {
    private IUserDao userDao = (UserDao) BeanFactory.getInstance("userDao");

    public int addUser(String name) {
        System.out.println("userService中的userDao" + userDao);
        return userDao.addUser(name);
    }
}
