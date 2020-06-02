package cn.peng.service.Impl;

import cn.peng.dao.IUserDao;
import cn.peng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("UserService")
@Scope("singleton")
public class UserServiceImpl implements IUserService {
    //    @Autowired
//    @Qualifier("userDao")
    @Resource(name = "userDao")

    private IUserDao userDao2;

    @Override
    public void addUser(String name) {
        System.out.println("UserService:");
        System.out.println("addUser:" + name);
        userDao2.addUser(name);
    }
}
