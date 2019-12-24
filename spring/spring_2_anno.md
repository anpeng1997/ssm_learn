# spring 注解配置

1. 配置文件：bean.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>

    <!--beans的约束不同，因为下方使用context:component-scan标签-->
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
            https://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            https://www.springframework.org/schema/context/spring-context.xsd">

        <!--扫描包里的注解-->
        <context:component-scan base-package="cn.peng.service.Impl"></context:component-scan>
    </beans>
    ```

2. 实现类上使用`@Component()`注解

    * 作用:用于把当前类对象存入spring容器中

    * 属性:
        * value:用于指定bean的id,当我们不写时，它默认值是当前类名，且首字母小写

3. 实现类上使用`@Controller`,`@Service`,`@Repository`这三种方式效果与`@Component`一致，只是spring给我们提供明确三层使用的注解，使我们三层对象更加清晰，分别对应的是：表现层、服务层、持久层。  

4. 依赖注入：

    1. 在所需依赖的变量上使用`@Autowired`:
        > 自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功，否则会抛出异常。  
        假如有两个及以上的bean对象和要注入的变量类型匹配，那么spring框架就会按照变量名去匹配，一个都没有没有匹配上就会抛异常

        ```java
        @Component("UserService")
        public class UserServiceImpl implements IUserService {
        @Autowired
        private IUserDao userDao;

        @Override
            public void addUser(String name) {
            System.out.println("UserService:");
            System.out.println("addUser:"+name);
            userDao.addUser(name);
            }
        }
        ```

    2. 当然我们也可以严格一点指定名称，这时候要结合另外一个注解一起使用

        ```java
        //这里就使用的userDao,就不用spring框架帮我们去匹配
        @Autowired
        @Qualifier("userDao")
        private IUserDao userDao2;
        ```

    3. 或者使用更简单的一个注解`@Resource`(属性是name)

        ```java
        @Resource(name = "userDao")
        private IUserDao userDao2;
        ```

    * 以上三种方式只能注入bean类型的数据，基本类型和String类型无法使用以上方式注入，集合类型则只能用xml来实现。

5. `@Value` ：用于注入基本类型和String类型的数据
    * 属性： value：用于指定数据的值。它可以使用spring中SpEL(也就是spring的el表达式）SpEL的写法：${表达式}

6. `@Scope` 注解设置作用范围，默认值是singleton；多例prototype

7. 与生命周期有关的：

    * `@PreDestroy` :指定销毁方法
    * `@PostConstruct` :指定初始化方法

