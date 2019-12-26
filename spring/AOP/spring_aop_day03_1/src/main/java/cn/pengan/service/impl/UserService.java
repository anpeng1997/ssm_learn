package cn.pengan.service.impl;

import cn.pengan.service.IUserService;

public class UserService implements IUserService {
    public void add(String name) {
        System.out.println("add" + name);
    }

    public int delete(int id) {
        System.out.println("delete" + id);
        return id;
    }

    public void update() {
        System.out.println("update.....");
    }
}
