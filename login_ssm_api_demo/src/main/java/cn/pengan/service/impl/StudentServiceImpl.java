package cn.pengan.service.impl;

import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.Pagination;
import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentDao studentDao;


    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public int saveStudent(Student student) {
        return studentDao.saveStudent(student);
    }

    public Pagination findStudentPage(int pageSize, int offset) {
        Pagination result = new Pagination();
        int count = studentDao.findStudentCount();
        result.setTotalCount(count);
        int totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        result.setTotalPage(totalPage);
        List<Student> students = studentDao.findStudentPage(pageSize, offset);
        result.setStudents(students);
        return result;
    }

    public int findStudentCount() {
        return studentDao.findStudentCount();
    }

    public Student findStudentById(int id) {
        return studentDao.findStudentById(id);
    }

    public void deleteStudentById(Integer[] ids) {
        studentDao.deleteStudentById(ids);
    }
}
