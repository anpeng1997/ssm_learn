package cn.pengan.service.impl;

import cn.pengan.service.IUserService;

import java.util.*;

public class UserService2 implements IUserService {
    private Map setMap;
    private List setList;
    private String[] setStrs;
    private Set setSet;

    public void setSetMap(Map setMap) {
        this.setMap = setMap;
    }

    public void setSetList(List setList) {
        this.setList = setList;
    }

    public void setSetStrs(String[] setStrs) {
        this.setStrs = setStrs;
    }

    public void setSetSet(Set setSet) {
        this.setSet = setSet;
    }

    public void print() {
        System.out.println("String[]");
        System.out.println(Arrays.toString(setStrs));
        System.out.println("map:");
        System.out.println(setMap);
        System.out.println("list:");
        System.out.println(setList);
        System.out.println("set:");
        System.out.println(setSet);
    }

    public int addUser(String name) {
        System.out.println("userService中的userDao:" + name);
        return 1;
    }
}
