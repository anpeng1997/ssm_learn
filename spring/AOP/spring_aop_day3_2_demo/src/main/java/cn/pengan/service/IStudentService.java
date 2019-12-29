package cn.pengan.service;

import cn.pengan.domain.Student;

import java.util.List;

public interface IStudentService {
    void transactionTransfer(String transferee,int money,String receive);
}
