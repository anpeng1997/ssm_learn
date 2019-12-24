package cn.pengan.service;

import cn.pengan.domain.Student;

import java.util.List;

public interface IStudentService {
    Student findById(Integer id);

    List<Student> findAll();

    void update(Student student);

    void add(Student student);

    void delete(Integer id);
}
