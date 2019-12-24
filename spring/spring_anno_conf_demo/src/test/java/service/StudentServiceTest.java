package service;

import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentServiceTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    IStudentService studentService = context.getBean("studentService", IStudentService.class);
    @Test
    public void testUpdata(){
        studentService.delete(22);
    }

    @Test
    public void testAdd(){
        Student student = new Student();
        student.setName("test11");
        student.setAge(20);
        student.setScore(100);
        IStudentService studentService = context.getBean("studentService", IStudentService.class);
        studentService.add(student);
        System.out.println("ok");
    }

    @Test
    public void testFindById() {
        Student s = studentService.findById(17);
        System.out.println(s);
    }
}
