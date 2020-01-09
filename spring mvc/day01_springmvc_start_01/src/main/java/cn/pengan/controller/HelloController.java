package cn.pengan.controller;

import cn.pengan.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class HelloController {

    @RequestMapping(path = "/hello",method = {RequestMethod.GET})
    public String sayHello() {
        return "success";
    }
    @RequestMapping(path = "/login",method = {RequestMethod.POST})
    public String login(User user) {
        System.out.println(user);
        return "success";
    }

    @RequestMapping(path = "/servletapi")
    public String servletAPI(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        System.out.println(session);
        System.out.println(response);
        return "success";
    }

    @RequestMapping(path = "/requestParam")
    public String requestParam(@RequestParam(name = "name",required = true) String username){
        System.out.printf(username);
        return "success";
    }
    @RequestMapping(path = "/requestBody")
    public String requestBody(@RequestBody String username){
        return "success";
    }

    @RequestMapping(path = "/Variable/{id}")
    public String Variable(@PathVariable("id") String userId){
        return "success";
    }
}
