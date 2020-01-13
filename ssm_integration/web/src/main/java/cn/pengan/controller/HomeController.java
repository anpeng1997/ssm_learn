package cn.pengan.controller;

import cn.pengan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.pengan.domain.User;

import java.util.List;

@Controller
@RequestMapping(path = "/home")
public class HomeController {

    @Autowired
    private IUserService userService;

    @RequestMapping(path = "/index")
    public String index(User user){
        userService.saveUser(user);
        return "/home/index";
    }
}
