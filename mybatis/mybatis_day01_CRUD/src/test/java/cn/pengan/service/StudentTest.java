package cn.pengan.service;

import cn.pengan.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class StudentTest {
    ApplicationContext applicationContext;
    IStudentService studentService;
    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        studentService = applicationContext.getBean("studentService", IStudentService.class);
    }

    @Test
    public void findAll(){
        System.out.println(studentService);
        List<Student> all = studentService.findAll();
        System.out.println(all);
        for (Student student : all) {
            System.out.println(student);
        }
    }

    @Test
    public void findById(){
        Student byId = studentService.findById(4);
        System.out.println(byId);
    }

    @Test
    public void update(){
        Student student=new Student();
        student.setId(4);
        student.setName("123456");
        student.setScore(600);
        student.setAge(55);
        studentService.update(student);
    }
    @Test
    public void delete(){
        studentService.delete(5);
    }

    @Test
    public void add(){
        Student student=new Student();
        student.setId(4);
        student.setName("zhangshan");
        student.setScore(600);
        student.setAge(66);
        studentService.add(student);
    }
}
