package cn.pengan.main;

import cn.peng.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("first...");
        ApplicationContext contxt = new ClassPathXmlApplicationContext("bean.xml");
        IUserService userService = contxt.getBean("UserService", IUserService.class);
        userService.addUser("pengan");
    }
}
