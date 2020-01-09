# spring mvc中的异常处理

* 异常处理

```java

//自定义一个类实现HandlerExcetionResolver接口
//在将它注入spring容器中
public class HandleException implements HandlerExceptionResolver {

    //当程序出现异常时，就会执行该方法，该方法中的返回参数可以跳转到指定的错误页面，还有显示指定的错误信息
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
```

```java
public class MyExecption extends Exception  {
    private String message;

    public MyExecption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
```

```xml
<!-- 将它注入spring容器中 -->
<bean id="exception" class="cn.pengan.exception.HandleException"></bean>
```

```java
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
```