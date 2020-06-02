package cn.pengan.controller;

import cn.pengan.domain.Student;
import cn.pengan.service.IStudentService;
import cn.pengan.viewModel.ApiResultModel;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/student")
public class StudentsController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping(path = "/getall", method = {RequestMethod.GET})
    public @ResponseBody
    List<Student> getAll() {
        List<Student> all = studentService.findAll();
        return all;
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Student getById(@PathVariable("id") Integer id) {
        return studentService.findStudentById(id);
    }

}
