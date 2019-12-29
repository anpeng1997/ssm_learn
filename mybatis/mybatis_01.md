# mybatis 项目的搭建及初始化

1. 导入mybatis所需的jar包

    ```xml
         <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.3</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.17</version>
        </dependency>
    ```

2. 创建mybatis配置文件

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">

    <configuration>
        <environments default="mysql">
            <environment id="mysql">
                <transactionManager type="JDBC"></transactionManager>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://127.0.0.1:3306/study"/>
                    <property name="username" value="root"/>
                    <property name="password" value="root"/>
                </dataSource>
            </environment>
        </environments>
        <mappers>
            <mapper resource="cn/pengan/dao/IStudentDao.xml"></mapper>
        </mappers>
    </configuration>
    ```

3. 创建dao对应的映射配置文件

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

    <mapper namespace="cn.pengan.dao.IStudentDao">
        <select id="findAll">
            select * from students;
        </select>
    </mapper>
    ```

4. 环境搭建的注意事项
    * mybatis的映射配置文件的位置必须和dao接口的包目录相同

    * 映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名

    * 映射配置文件的操作配置（select）,id属性的取值必须是dao接口的方法名

    * 当我们遵循以上三点后，我们在开发当中就不需要自己实现dao的实现类（当然我们也可以自己编写实现类）

5. 使用注解的入门

    >在同一个接口的同一个方法上使用注解后就不能在xml配置中在配置该方法

    * 在dao接口的方法上使用`@Select()`注解，将要执行的sql语句作为参数传入

    ```java
    @Select("select * from students;")
    List<Student> findAll();
    ```

    * 再在SqlMapConfig.xml配置文件中的Mapper节点的属性：class赋值

    ```xml
    <mapper class="cn.pengan.dao.IStudentDao"></mapper>
    ```
