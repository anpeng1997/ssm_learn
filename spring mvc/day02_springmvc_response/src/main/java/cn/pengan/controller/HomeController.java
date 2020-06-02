package cn.pengan.controller;

import cn.pengan.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/index")
    public String index(Model model) {
        User user = new User();
        user.setAge(20);
        user.setUsername("哈哈哈");
        model.addAttribute("user", user);
        return "index";

    }

    @RequestMapping("/modelandview")
    public ModelAndView modelAndViewTest() {
        ModelAndView result = new ModelAndView();
        User user = new User();
        user.setAge(20);
        user.setUsername("xixixiix");
        result.addObject("user", user);
        result.setViewName("modelandview");
        return result;
    }

    @RequestMapping("/getjsondata")
    public @ResponseBody
    User getJsonData() {
        User user = new User();
        user.setAge(20);
        user.setUsername("xixixiix");
        return user;
    }
}
