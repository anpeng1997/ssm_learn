package cn.peng.dao.impl;

import cn.peng.dao.IUserDao;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao implements IUserDao {
    @Override
    public void addUser(String name) {
        System.out.println("UserDao");
        System.out.println("addUser:" + name);
    }
}
