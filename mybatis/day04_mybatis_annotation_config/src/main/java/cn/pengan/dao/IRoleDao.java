package cn.pengan.dao;

import cn.pengan.domain.Role;

import java.util.List;

public interface IRoleDao {
    List<Role> findAll();

    List<Role> findAllAndUser();
}
