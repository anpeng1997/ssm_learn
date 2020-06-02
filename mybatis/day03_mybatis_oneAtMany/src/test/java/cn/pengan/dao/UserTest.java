package cn.pengan.dao;

import cn.pengan.domain.Account;
import cn.pengan.domain.Role;
import cn.pengan.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    InputStream resourceAsStream;
    SqlSession session;
    IUserDao userDao;

    @Before
    public void init() {
        try {
            resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
        session = factory.openSession(true);
        //使用SqlSession创建的dao接口代理的对象
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        resourceAsStream.close();
        session.close();
    }

    @Test
    public void findAll() {

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("...........................");
            System.out.println(user);
            for (Account account : user.getAccounts()) {
                System.out.println("      " + account);
            }
        }

    }

    @Test
    public void findAllAndUser() {
        List<User> allUserAndRole = userDao.findAllUserAndRole();
        for (User user : allUserAndRole) {
            System.out.println("-----------------");
            System.out.println(user);
            for (Role role : user.getRoles()) {
                System.out.println(role);
            }
        }
    }
}
