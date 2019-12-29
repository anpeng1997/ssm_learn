package cn.pengan.dao.impl;

import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.Student;
import cn.pengan.utils.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class StudentDao implements IStudentDao {
    private JdbcTemplate jdbcTemplate ;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void updateStudent(Student student) {
        jdbcTemplate.update("update students set name=?, age=?,score=?  where id = ?;",  student.getName(),student.getAge(),student.getScore(),student.getId());
    }

    public Student getStudentByName(String name) {
        List<Student> students = jdbcTemplate.query("select * from students where name = ?", new BeanPropertyRowMapper<Student>(Student.class), name);
        if (students.isEmpty()) {
            return null;
        }
        if (students.size() > 1) {
            new RuntimeException("查询多个用户名相同");
        }
        return students.get(0);
    }
}
