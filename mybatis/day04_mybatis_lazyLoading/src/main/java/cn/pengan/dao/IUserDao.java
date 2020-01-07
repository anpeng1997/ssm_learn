package cn.pengan.dao;

import cn.pengan.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();

    List<User> findAllUserAndRole();

    User findUserById(int uid);
}
