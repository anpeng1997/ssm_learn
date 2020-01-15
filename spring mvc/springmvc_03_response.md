# spring mvc response 类型

## 控制器与jsp页面共享对象

```java
    @RequestMapping("/index")
    // 通过Model类来共享对象
    public String index(Model model){
        User user=new User();
        user.setAge(20);
        user.setUsername("哈哈哈");
        model.addAttribute("user",user);
        return "index";
    }
```

```html
    <!-- jsp -->
    ${user.username}
    <br/>
    ${user.age}
```

## 方法返回值为void

>默认是去寻找与path路径相同的jsp文件，当然我们可以使用的ServletAPI：HttpServletResponse的sendRedirect()等方法

## 方法返回为String

>寻找与返回值相同名的jsp文件

* forward与redirect关键字

```java
 //转发要写全路径
 return "forward:/WEB-INF/pages/index.jsp";
 //重定向
 return "redirect:/index.jsp";
```

## 方法返回值为ModelAndView类型

>可以在这个类中添加Model和指定要跳转的view，  
默认是使用`forward:`关键字进行转发  
我们也可以使用`redirect:`进行重定向：  
如果要实现重定向，重定向的方式在request域中的数据会失效，此时可以通过ModelAndView来传递数据，但是只能传递基本数据类型和String类型，因为spring mvc会将传递的数据以请求参数的方式放到url的后面，此时任何数据类型都会被转换为普通的字符串。另外，我们在jsp中取值的时候需要使用 EL 表达式中的请求参数param读取。`${param.name}`

```java
  @RequestMapping("/modelandview")
    public ModelAndView modelAndViewTest(){
        ModelAndView result = new ModelAndView();
        User user=new User();
        user.setAge(20);
        user.setUsername("xixixiix");
        result.addObject("user",user);
        //默认是使用forward:关键字进行转发
        //result.setViewName("forward:modelandview");
        result.setViewName("modelandview");
        return result;
    }
```

## 在springmvc.xml中配置访问静态资源

```xml
<mvc:resources location="/static/" mapping="/static/**" ></mvc:resources>
```

## 返回类型是bean对象,序列化成json对象响应

```java
    @RequestMapping("/getjsondata")
    //使用@ResponseBody响应，再要导入jackson jar坐标
    //spring mvc会帮我们自动将bean对象转换成json
    public @ResponseBody User getJsonData(){
        User user=new User();
        user.setAge(20);
        user.setUsername("xixixiix");
        return user;
    }
```

```xml
<dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.10.1</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>2.10.1</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.10.1</version>
</dependency>
```