package cn.pengan.service.impl;

import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {

    private IStudentDao studentDao;

    public void setStudentDao(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student findById(Integer id) {
        return studentDao.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void add(Student student) {
        studentDao.add(student);
    }

    @Override
    public void delete(Integer id) {
        studentDao.delete(id);
    }
}
