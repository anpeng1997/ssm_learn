package cn.pengan.service.impl;

import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentService implements IStudentService {

    @Autowired
    private IStudentDao studentDao;

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
