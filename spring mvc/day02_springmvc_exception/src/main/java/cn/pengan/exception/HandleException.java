package cn.pengan.exception;

import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        MyExecption ex=null;
        if (e instanceof MyExecption){
            ex= (MyExecption) e;
        }else{
            try {
                throw new MyExecption("出现错误！");
            } catch (MyExecption myExecption) {
                ex = myExecption;
                myExecption.printStackTrace();
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errormsg",e.getMessage());
        return modelAndView;
    }
}
