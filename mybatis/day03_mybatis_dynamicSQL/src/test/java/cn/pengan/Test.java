package cn.pengan;


import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.QueryAO;
import cn.pengan.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import sun.misc.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class Test {
    InputStream resourceAsStream;
    SqlSession session;

    @Before
    public void init(){
        try {
            resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
        session = factory.openSession(true);
    }

    @org.junit.Test
    public void findAll(){
        //使用SqlSession创建的dao接口代理的对象
        IStudentDao studentDao = session.getMapper(IStudentDao.class);
        List<Student> students = studentDao.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
        session.close();
        try {
            resourceAsStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void search(){
        IStudentDao studentDao = session.getMapper(IStudentDao.class);
        Student student =new Student();
        //student.setName("zhangshan");
        List<Student> search = studentDao.search(student);
        for (Student s : search) {
            System.out.println(s);
        }
    }

    @org.junit.Test
    public void searchInIds(){
        IStudentDao studentDao = session.getMapper(IStudentDao.class);
        List<Integer> ids =new ArrayList<Integer>();
        ids.add(4);
        ids.add(6);
        QueryAO queryAO =new QueryAO();
        queryAO.setIds(ids);
        List<Student> students = studentDao.searchInIds(queryAO);
        for (Student student : students) {
            System.out.println(student);
        }
    }


}
