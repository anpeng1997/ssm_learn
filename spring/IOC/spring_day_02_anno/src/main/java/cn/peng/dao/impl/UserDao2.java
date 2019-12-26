package cn.peng.dao.impl;

import cn.peng.dao.IUserDao;
import org.springframework.stereotype.Repository;

@Repository("userDao2")
public class UserDao2 implements IUserDao {
    @Override
    public void addUser(String name) {
        System.out.println("UserDao222222222222222");
        System.out.println("addUser:" + name);
    }
}
