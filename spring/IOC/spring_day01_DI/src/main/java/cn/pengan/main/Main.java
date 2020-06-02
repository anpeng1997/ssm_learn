package cn.pengan.main;

import cn.pengan.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        userService.print();
    }
}
