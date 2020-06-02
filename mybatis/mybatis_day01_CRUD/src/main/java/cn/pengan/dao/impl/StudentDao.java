package cn.pengan.dao.impl;

import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.Student;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class StudentDao implements IStudentDao {

    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    private SqlSessionTemplate sessionTemplate;

    public Student findById(Integer id) {
        return sessionTemplate.selectOne("cn.pengan.dao.IStudentDao.findById", id);
    }

    public List<Student> findAll() {
        return sessionTemplate.selectList("cn.pengan.dao.IStudentDao.findAll");
    }

    public void update(Student student) {
        sessionTemplate.update("cn.pengan.dao.IStudentDao.update", student);
    }

    public void add(Student student) {
        sessionTemplate.insert("cn.pengan.dao.IStudentDao.add", student);
    }

    public void delete(Integer id) {
        sessionTemplate.delete("cn.pengan.dao.IStudentDao.delete", id);
    }
}
