package cn.pengan.dao;

import cn.pengan.domain.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();
}
