# spring 与 junit的整合

1. 导入spring整合junit的jar(坐标),spring 5.0时，则spring-test要4.12及以上

    ```xml
     <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>5.0.2.RELEASE</version>
        <scope>test</scope>
    </dependency>
    ```

2. 使用Junit提供的一个注解把原有的main方法替换了，替换成spring提供的

    * `@Runwith`

3. 告知spring的运行器，spring和ioc创建是基于xml还是注解的，并且说明位置

    * `@ContextConfiguration`
        * 属性：
        >locations：指定xml文件的位置，加上classpath关键字，表示在类路径下  
        classes：指定注解类所在的位置

        ```java
        @RunWith(SpringJUnit4ClassRunner.class)
        //这里使用的是注解的方式配置，所以给classes赋值
        @ContextConfiguration(classes = springConfig.class)
        public class StudentServiceTest {
            @Autowired
            private IStudentService studentService;
        }
        ```  
