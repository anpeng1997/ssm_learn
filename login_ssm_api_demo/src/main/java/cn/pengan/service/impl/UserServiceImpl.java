package cn.pengan.service.impl;

import cn.pengan.dao.IUserDao;
import cn.pengan.domain.User;
import cn.pengan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    public User findUserByNamePwd(String name, String password) {
        return userDao.findUserByNamePwd(name,password);
    }
}
