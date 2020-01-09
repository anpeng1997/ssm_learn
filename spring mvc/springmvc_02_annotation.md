# annotaction

* `@Contorller`

>表示该类是一个控制器，程序启动时就会扫描到，实例化到spring容器中

```java
@Controller
public class HelloController {

}
```

* `@RequestMapping()`

>用于建立请求url和处理方法之间的对应关系

1. `path`：路径

2. `method`：处理那种请求

3. `params`：请求必须含有的参数和值必须一样

4. `headers`：设置必须含有的请求头

```java
@Controller
@RequestMapping("/user")
public class HelloController {

    @RequestMapping(path = "/hello",method = {RequestMethod.GET},params = {"name"})
    public String sayHello() {
        return "success";
    }
}
```

* `@RequestParam`

>把请求中指定的名称的参数给控制器中的形参赋值

```java
 @RequestMapping(path = "/requestParam")
    //将传递过来的参数name的值赋给username
    public String requestParam(@RequestParam(name = "name",required = true) String username){
        System.out.printf(username);
        return "success";
    }
```

* `@RequestBody`

>表示获取请求中的请求体，不适用与get方法

```java
 @RequestMapping(path = "/requestParam")
    //将传递过来的参数name的值赋给username
    public String requestParam(@RequestBody String user){
        //获取到的是 name=xxx&age=XX的格式
        return "success";
    }
```

* `@PathVariable()`

> RESTful API

```java
@RequestMapping(path = "/Variable/{id}")
public String Variable(@PathVariable("id") String userId){
    return "success";
}
```

* `@RequestHeader()`

> 获取请求头的值

* `@CookieValue()`

* `@ModelAttribute`

> 既可以使用在方法上，也可以使用在参数上。使用在方法上表示该方法会在所有的的方法执行前执行，比如:当一个请求过来时，会先执行标注了 @ModelAttribute 的方法在执行相对应的处理方法。使用在形参上时，代表先执行的方法上有返回值，使用该注解的形参可以获得该返回值

* `@SessionAttributes`

> 用于多次执行控制器方法间的参数共享

## 传递复杂对象

```html
<form action="${ pageContext.request.contextPath }/user/login" method="post">
username: <input type="text" name="username">
password: <input type="text" name="password">
account：<input type="text" name="account.money">
account1: <input type="text" name="accounts[0].money">

account2: <input type="text" name="accounts[1].money">
<input type="submit" value="ok">
</form>
```

```java
private String username;
private String password;
private Account account;
private List<Account> accounts;
```

## 获取ServletAPI

>想要获取那个API就在方法的参数上添加

```java
    @RequestMapping(path = "/servletapi")
    public String servletAPI(HttpServletRequest request, HttpServletResponse response){
        return "success";
    }
```
