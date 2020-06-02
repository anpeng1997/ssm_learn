package cn.pengan.dao;

import cn.pengan.domain.Account;
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

public class AccountTest {
    InputStream resourceAsStream;
    SqlSession session;

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
    }

    @After
    public void destroy() throws IOException {
        resourceAsStream.close();
        session.close();
    }

    @Test
    public void findAll() {
        //使用SqlSession创建的dao接口代理的对象
        IAccountDao accountDao = session.getMapper(IAccountDao.class);
        List<Account> accounts = accountDao.findAll();
        System.out.println(accounts.size());
        for (Account account : accounts) {
            System.out.println(account);
        }

    }
}
