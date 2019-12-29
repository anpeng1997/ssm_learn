package cn.pengan.service.impl;

import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;
import cn.pengan.utils.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class StudentService implements IStudentService {

    private IStudentDao studentDao;

    public void setStudentDao(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void transactionTransfer(String transferee, int money, String receive) {
        Student transf = studentDao.getStudentByName(transferee);
        Student rec = studentDao.getStudentByName(receive);
        transf.setScore(transf.getScore() - money);
        rec.setScore(rec.getScore() + money);
        studentDao.updateStudent(transf);
        int i = 5 / 0;
        studentDao.updateStudent(rec);
    }
}
