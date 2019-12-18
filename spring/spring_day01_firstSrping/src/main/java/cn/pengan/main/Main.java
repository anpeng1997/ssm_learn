package cn.pengan.main;

import cn.pengan.factory.BeanFactory;
import cn.pengan.service.IUserService;

public class Main {
    private static IUserService userService = (IUserService) BeanFactory.getInstance("userService");
    public static void main(String[] args) {
        System.out.println("main中的userService" + userService);
        int result = userService.addUser("asdasdas");
        System.out.println("result" + result);
    }
}
