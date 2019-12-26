package cn.pengan;

import cn.pengan.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    @Test
    public void testUserAOP() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
//        userService.add("abc");
        int delete = userService.delete(5555);
        System.out.println("result:" + delete);
    }

}
