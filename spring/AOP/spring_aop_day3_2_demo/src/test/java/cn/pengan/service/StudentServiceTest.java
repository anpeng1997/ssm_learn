package cn.pengan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class StudentServiceTest {


    @Test
    public void testTransactionTransfer() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        IStudentService studentService = applicationContext.getBean("studentService", IStudentService.class);
        studentService.transactionTransfer("imm", 10, "tom2");
        System.out.println("ok");
    }
}
