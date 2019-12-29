package cn.pengan.dao;

import cn.pengan.domain.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IStudentDao {
    Student findById(Integer id);

    @Select("select * from students;")
    List<Student> findAll();

    void update(Student student);

    void add(Student student);

    void delete(Integer id);
}
