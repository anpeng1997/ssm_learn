package cn.pengan.service;

import cn.pengan.domain.User;

public interface IUserService {
    public User findUserByNamePwd(String name, String password);
}
