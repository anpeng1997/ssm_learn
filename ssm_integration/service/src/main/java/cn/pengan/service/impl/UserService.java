package cn.pengan.service.impl;

import cn.pengan.dao.IUserDao;
import cn.pengan.domain.User;
import cn.pengan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    @Autowired()
    private IUserDao userDao;

    @Override
    public void saveUser(User user) {
        System.out.println("UserService中的saveUser.....");
        try {
            userDao.saveUser(user);
            int i = 1 / 0;
            userDao.saveUser(user);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new RuntimeException("出现异常了");
        }

    }

    @Override
    public List<User> findAll() {
        System.out.println("UserService中的findAll.....");
        return userDao.findAll();
    }
}
