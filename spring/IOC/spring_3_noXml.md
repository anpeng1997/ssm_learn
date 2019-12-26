# spring 没有xml文件中配置IOC（全部使用注解的配置方式）

* `@Configuration`
    > 指定当前类是一个配置类  
    > 当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写.33333333333333333

* `@ComponetScan`
    >用于通过注解指定spring在创建容器时要扫描的包  
    它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包。  
    我们使用此注解就等同于在xml中配置了:  
    <context:component-scan base-package="com.itheima"></context:component-scan>

* `@Bean`
    >用于把当前方法的返回值作为bean对象存入spring的ioc容器中  
     属性:  
     name:用于指定bean的id。当不写时，默认值是当前方法的名称  
     细节：  
     当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象。  
     查找的方式和Autowired注解的作用是一样的

* `@Import`
    >用于导入其他的配置类  
     属性：  
     value：用于指定其他配置类的字节码。当我们使用Import的注解之后，有Import注解的类就父配置类，而导入的都是子配置类

    ```java
        //这样以来springConfig就是一个空类，而其它的配置则细分至每个子类中
        @Configuration
        @ComponentScan(basePackages = {"cn.pengan"})
        @Import(JdbcConfig.class)
        public class springConfig {
        }
    ```

    ```java
        public class JdbcConfig {
        @Bean
        @Scope("prototype")
        public JdbcTemplate createJdbcTemplate(DataSource dataSource){
            return new JdbcTemplate(dataSource);
        }
        @Bean
        public DataSource createDataSource(){
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/study");
            druidDataSource.setPassword("root");
            druidDataSource.setUsername("root");
            druidDataSource.setMaxActive(20);
            return druidDataSource;
        }
        }
    ```

* `PropertySource`
    >作用：用于指定properties文件的位置  
     属性:  
      value：指定文件的名称和路径。  
      关键字：classpath，表示类路径下

    ```java
        @ComponentScan("com.itheima")
        @Import(JdbcConfig.class)
        @PropertySource("classpath:jdbcConfig.properties")
        public class SpringConfiguration {
        }
    ```

    >读取properties中的配置，使用`@Value`标签

    ```java
        //在需要使用配置的springConfig配置类中
        //与properties中key对应
        @Value("${url}")
        private String url;
        @Value("${driverClassName}")
        private String driverClassName;
        @Value("${username}")
        private String username;
        @Value("${password}")
        private String password;
        @Value("${maxActive}")
        private Integer maxActive;
        @Value("${maxWait}")
        private Integer maxWait;
        @Value("${initialSize}")
        private Integer initialSize;
    ```


1. 创建一个配置类：springConfig

    ```java
        //标记该类为spring 配置类
        @Configuration
        //要扫描的包
        @ComponentScan(basePackages = {"cn.pengan"})
        public class springConfig {

            @Bean
            @Scope("prototype")
            //需要的参数，spring框架会在已经注入的类中查找类型相同的（假入下面createDataSource方法没有生命，则该方法就会有一个编译期间的错误）
            public JdbcTemplate createJdbcTemplate(DataSource dataSource){
                return new JdbcTemplate(dataSource);
            }

            @Bean
            public DataSource createDataSource(){
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/study");
                druidDataSource.setPassword("root");
                druidDataSource.setUsername("root");
                druidDataSource.setMaxActive(20);
                return druidDataSource;
            }
        }
    ```

2. 获取注解配置对象

    ```java
    ApplicationContext context = new AnnotationConfigApplicationContext(springConfig.class);
    ```
