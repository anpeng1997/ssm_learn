package cn.pengan.testService;

import cn.pengan.domain.User;
import cn.pengan.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserService {


    @Test
    public void testUserFindAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IUserService userService = (IUserService) context.getBean("userService");
        User user = new User();
        user.setUsername("kkk");
        user.setAge(20);
        user.setSex("m");
        userService.saveUser(user);
    }
}
