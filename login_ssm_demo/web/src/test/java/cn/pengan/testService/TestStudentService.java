package cn.pengan.testService;

import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStudentService {

    @Test
    public void testService(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
         IStudentService service= (IStudentService)classPathXmlApplicationContext.getBean("studentService");
        int studentCount = service.findStudentCount();
        System.out.println(studentCount);
    }

    @Test
    public void testService1(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IStudentService service= (IStudentService)classPathXmlApplicationContext.getBean("studentService");
        service.deleteStudentById(new Integer[]{4});
    }
    @Test
    public void testAddService(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IStudentService service= (IStudentService)classPathXmlApplicationContext.getBean("studentService");
        Student student = new Student(null, "kkk", 22, 100);
        service.saveStudent(student);
    }
}
