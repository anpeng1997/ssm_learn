package cn.pengan.dao;

import cn.pengan.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentDao")
public interface IStudentDao {
    public List<Student> findAll();

    public int saveStudent(Student student);

    public List<Student> findStudentPage(int pageSize, int offset);

    public int findStudentCount();

    public Student findStudentById(int id);

    public void deleteStudentById(Integer[] ids);
}
