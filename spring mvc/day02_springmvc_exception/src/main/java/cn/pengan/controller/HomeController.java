package cn.pengan.controller;

import cn.pengan.exception.MyExecption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/home")
@Controller
public class HomeController {

    @RequestMapping(path = "/index")
    public String index() throws MyExecption{
        try {
            int i = 1/0;
        }catch (Exception EX){
            EX.printStackTrace();
            throw new MyExecption("不能除以0");
        }
        return "index";
    }
}



