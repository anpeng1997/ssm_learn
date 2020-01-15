package cn.pengan.controller;

import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/api/student")
public class StudentsController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping(path = "/getall",method = {RequestMethod.GET})
    public @ResponseBody List<Student> getAll(){
        List<Student> all = studentService.findAll();
        return all;
    }
}
