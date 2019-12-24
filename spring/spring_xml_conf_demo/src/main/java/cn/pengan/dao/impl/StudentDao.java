package cn.pengan.dao.impl;

import cn.pengan.dao.IStudentDao;
import cn.pengan.domain.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class StudentDao implements IStudentDao {
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    @Override
    public Student findById(Integer id) {
        Student student = jdbcTemplate.queryForObject("select * from students where id= ?", BeanPropertyRowMapper.newInstance(Student.class), id);
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = jdbcTemplate.query("select * from students;", BeanPropertyRowMapper.newInstance(Student.class));
        return students;
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update("update studens set name = ?, age = ?,score = ? where id = ?",
                student.getName(), student.getAge(), student.getScore(), student.getId());
    }

    @Override
    public void add(Student student) {
        jdbcTemplate.update("insert into students values (null ,?,?,?);",
                student.getName(), student.getAge(), student.getScore());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("delete from students where id = ?", id);
    }
}
