package cn.pengan.service.impl;

import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {
    public StudentService(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }

    private IStudentDao studentDao;

    public Student findById(Integer id) {
        return studentDao.findById(id);
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public void add(Student student) {
        studentDao.add(student);
    }

    public void delete(Integer id) {
        studentDao.delete(id);
    }
}
