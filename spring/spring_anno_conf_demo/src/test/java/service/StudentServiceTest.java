package service;

import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;
import config.springConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = springConfig.class)
public class StudentServiceTest {
//    ApplicationContext context = new AnnotationConfigApplicationContext(springConfig.class);
//    //ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//    IStudentService studentService = context.getBean("studentService", IStudentService.class);
    @Autowired
    private IStudentService studentService;

    @Test
    public void testUpdata(){
    }

    @Test
    public void testAdd(){
        Student student = new Student();
        student.setName("test11");
        student.setAge(20);
        student.setScore(100);
        studentService.add(student);
        System.out.println("ok");
    }

    @Test
    public void testFindById() {
        Student s = studentService.findById(17);
        System.out.println(s);
    }
}
