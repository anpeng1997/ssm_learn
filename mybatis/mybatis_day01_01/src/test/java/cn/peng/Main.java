package cn.peng;

import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.misc.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession session = factory.openSession();
        //使用SqlSession创建的dao接口代理的对象
        IStudentDao studentDao = session.getMapper(IStudentDao.class);
        List<Student> students = studentDao.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
        session.close();
        resourceAsStream.close();
    }
}
