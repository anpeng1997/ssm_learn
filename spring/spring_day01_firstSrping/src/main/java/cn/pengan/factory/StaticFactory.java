package cn.pengan.factory;

import cn.pengan.service.IUserService;
import cn.pengan.service.impl.UserService;

public class StaticFactory {
    public static IUserService getUserService() {
        return new UserService();
    }
}
