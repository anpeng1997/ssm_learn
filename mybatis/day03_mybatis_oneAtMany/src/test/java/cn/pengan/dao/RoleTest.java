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

public class RoleTest {
    InputStream resourceAsStream;
    SqlSession session;
    IRoleDao roleDao;

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
        roleDao = session.getMapper(IRoleDao.class);
    }

    @After
    public void destroy() throws IOException {
        resourceAsStream.close();
        session.close();
    }

    @Test
    public void findAll() {

        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println("...........................");
            System.out.println(role);
        }
    }

    @Test
    public void findAllAndUser() {
        List<Role> allAndUser = roleDao.findAllAndUser();
        for (Role role : allAndUser) {
            System.out.println("..................");
            System.out.println(role);
            for (User user : role.getUsers()) {
                System.out.println(user);
            }
        }
    }
}
