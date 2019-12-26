package cn.pengan.factory;

import cn.pengan.service.IUserService;
import cn.pengan.service.impl.UserService;

public class InstanceFactory {
    public IUserService getUserService() {
        return new UserService();
    }
}
