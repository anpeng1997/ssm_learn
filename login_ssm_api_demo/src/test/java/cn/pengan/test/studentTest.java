package cn.pengan.test;

import cn.pengan.service.IStudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class studentTest {
    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IStudentService service = (IStudentService) applicationContext.getBean("studentService");
        int studentCount = service.findStudentCount();
        System.out.println(studentCount);
    }
}
