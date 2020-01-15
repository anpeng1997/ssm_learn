package cn.pengan.service;

import cn.pengan.domain.Pagination;
import cn.pengan.domain.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> findAll();
    public int saveStudent(Student student);
    public Pagination findStudentPage(int pageSize, int offset);
    public int findStudentCount();
    public Student findStudentById(int id);
    public void deleteStudentById(Integer[] ids);
}
