package cn.pengan.dao;

import cn.pengan.domain.Student;

import java.util.List;

public interface IStudentDao {
    void updateStudent(Student student);

    Student getStudentByName(String name);
}
