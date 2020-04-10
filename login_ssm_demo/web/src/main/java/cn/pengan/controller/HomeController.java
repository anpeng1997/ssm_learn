package cn.pengan.controller;

import cn.pengan.domain.Pagination;
import cn.pengan.domain.Student;
import cn.pengan.domain.User;
import cn.pengan.service.IStudentService;
import cn.pengan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(path = "/home")
public class HomeController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IUserService userService;

    @RequestMapping(path = "/index")
    public String index() {
        return "home/index";
    }

    @RequestMapping(path = "/login", method = {RequestMethod.GET})
    public String login() {
        return "home/login";
    }

    @RequestMapping(path = "/login", method = {RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request, String name, String password, String verificationCode) {
        ModelAndView modelAndView = new ModelAndView();
        if (verificationCode == null || "".equals(verificationCode.trim())) {
            modelAndView.addObject("login_error", "验证码不能为空！");
            modelAndView.setViewName("home/login");
        } else {
            HttpSession session = request.getSession();
            String verification_code = session.getAttribute("verification_code").toString();
            session.removeAttribute("verification_code");
            if (verification_code == null || !verificationCode.equalsIgnoreCase(verification_code)) {
                modelAndView.addObject("login_error", "验证码输入错误");
                modelAndView.setViewName("home/login");
            } else {
                User user = userService.findUserByNamePwd(name, password);
                if (user != null) {
                    session.setAttribute("currentLoginUser", user);
                    modelAndView.setViewName("redirect:/home/index");
                } else {
                    modelAndView.addObject("login_error", "姓名或密码输入错误！");
                    modelAndView.setViewName("home/login");
                }
            }
        }
        return modelAndView;
    }

    @RequestMapping(path = "/studentlist", method = {RequestMethod.GET})
    public ModelAndView studentList(Integer pageNumber, Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        int pageNum = pageNumber == null ? 1 : pageNumber;
        int size = pageSize == null ? 5 : pageSize;
        int offset = (pageNum - 1) * size <= 0 ? 0 : (pageNum - 1) * size;
        Pagination studentPage = studentService.findStudentPage(size, offset);
        studentPage.setCurrentPage(pageNum);
        modelAndView.addObject("pageData", studentPage);
        modelAndView.setViewName("home/studentlist");
        return modelAndView;
    }

    @RequestMapping(path = "/deleteStudents")
    public String deleteStudents(Integer[] ids){
        studentService.deleteStudentById(ids);
        return "redirect:/home/studentlist";
    }

    @RequestMapping(path = "/addStudent",method = {RequestMethod.GET})
    public String add(){
        return "home/addStudent";
    }

    @RequestMapping(path = "/addStudent",method = {RequestMethod.POST})
    public String add(Student student){
        studentService.saveStudent(student);
        return "redirect:/home/studentlist";
    }
}
