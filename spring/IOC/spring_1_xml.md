# spring IOC xml配置  

* demo project: `spring_day01_firstSpring`

1. 在Maven的pom.xml配置文件中添加Spring的坐标

    ```xml
    <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>
    ```

2. 在resources文件夹下创建xml配置文件

    ```xml
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:p="http://www.springframework.org/schema/p"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
    https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userService" class="cn.pengan.service.impl.UserService"></bean>
    <bean id="userDao" class="cn.pengan.dao.impl.UserDao"></bean>
    </beans>
    ```

3. 代码中导入配置文件，并获得bean对象

    ```java
    //获取spring ioc核心容器对象
    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    IUserDao userDao = context.getBean("userDao", IUserDao.class);
    ```

    * 有3种方式获得核心容器对象
        * `ClassPathXmlApplicationContext` : 加载类路径下的配置文件。

        * `FileSystemXmlAPPlicationContext` : 加载磁盘上任意路径的配置文件（没有访问权限的不能加载）

        * `AnnotationConfigApplicationContext` : 使用注解配置的方式就用这种方式获取

    * 两种不同接口对象的区别
        * `ApplicathionContext` ：在单例对象中使用，因为在读取完配置文件后就会立即创建对象在内部

        * `BeanFactory` : 多例对象使用，创建对象采用延时加载的方式

## spring 中创建bean对象的三种方式细节

1. 第一种：spring会调用该类的默认构造函数创建对象，当该类没有空参的构造函数时，则会抛出异常

    ```xml
    <bean id="userService" class="cn.pengan.service.impl.UserService"></bean>
    <bean id="userDao" class="cn.pengan.dao.impl.UserDao"></bean>
    ```

2. 第二种：使用普通工厂中的方法创建对象（使用某个类中的方法创建对象，并存入Spring容器）当我们使用别人的jar包时，我们只有class字节码文件,我们并不知道它是否有默认的空参构造函数，我们也不能
改变它，所以我们只能通过普通工厂模式去创建它，或者调用它的方法创建它，spring框架再把它保存在容器当中

    ```xml
    <bean id="instanceFactory" class="cn.pengan.factory.InstanceFactory"></bean>
    <bean id="userService" factory-bean="instanceFactory" factory-method="getUserService"></bean>
    ```

3. 第三种：使用工厂类中的静态方法创建对象，原理与第二中方式类似，只不过是调用静态方法去创建实例而已

    ```xml
    <bean id="userService" class="cn.pengan.factory.StaticFactory" factory-method="getUserService"></bean>
    ```

## bean的作用范围

> bean标签的scope属性:用于指定bean的作用范围

* `singleton` 单例的（默认值）

* `prototype` 多例的

* `request` 作用于web应用的请求范围

* `session` 作用于web应用的会发范围

* `global-session` 作用与集群环境的会话范围（全局会话范围），当不是集群环境时，它就是session

## bean的生命周期

* 单例对象：

  * 出生：当容器创建时对象出生

  * 活着：只要容器还在，对象就一直再活着

  * 死亡：容器销毁，对象消失

  * 总结：单例的生命周期和容器相同

* 多例对象：

  * 出生：当我们要使用对象时，spring框架帮我们创建
  
  * 活着：对象只要再使用过程中，就一直存活者

  * 死亡：当对象长时间不使用时，且没有别的对象引用时，由java的GC回收

## 依赖注入

> 在当前类需要使用其它类的对象时，由spring为我们提供，我们只需要在配置文件中说明依赖关系的维护

1. 依赖注入的能注入的三类数据：

    * 基本类型和String

    * 其它bean类型（在配置文件中或者注解配置过的bean)

    * 复杂类型/集合类型

2. 注入的方式：

    * 使用构造函数提供

    ```xml
    <bean id="userService" class="cn.pengan.service.impl.UserService">
        <constructor-arg name="name" value="defaultName"></constructor-arg>
        <constructor-arg name="age" value="12"></constructor-arg>
         <constructor-arg name="birthday" ref="now"></constructor-arg>
    </bean>
    <bean id="now" class="java.util.Date"></bean>
    ```

    * 使用set方法提供   ---更常用

    ```xml
    <!--基本类型和String、bean-->
    <bean id="userService" class="cn.pengan.service.impl.UserService2">
        <property name="name" value="defaultName"></property>
        <property name="age" value="12"></property>
        <property name="birthday" ref="now"></property>
    </bean>
    <bean id="now" class="java.util.Date"></bean>

    <!--复杂类型/集合类型-->
    <bean id="userService" class="cn.pengan.service.impl.UserService2">
        <property name="setStrs">
            <array>
                <value>aaa</value>
                <value>cccc</value>
            </array>
        </property>
        <property name="setMap">
            <map>
                <entry key="key1" value="value1"></entry>
                <entry key="key2" value="value2"></entry>
                <entry key="key3" value="value3"></entry>
            </map>
        </property>
        <property name="setList">
            <list>
                <value>HHHH</value>
                <value>LLLL</value>
            </list>
        </property>
        <property name="setSet">
            <set>
                <value>PPPP</value>
                <value>OOOOO</value>
            </set>
        </property>
    </bean>
    ```

    * 使用注解提供

## spring ioc xml配置demo  

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--service 的配置-->
    <bean id="studentService" class="cn.pengan.service.impl.StudentService">
        <property name="studentDao" ref="studentDao"></property>
    </bean>
    <!--依赖dao层，所以要注入-->
    <bean id="studentDao" class="cn.pengan.dao.impl.StudentDao">
    <!--dao中又依赖jdbcTemplate-->
        <property name="jdbcTemplate" ref="jdbcTemplate"></property>
    </bean>
    <!--dao层的配置,设置成多例的，以防止多个dao调用同一个，产生线程安全问题-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate" scope="prototype">
        <constructor-arg name="dataSource" ref="dataSource"></constructor-arg>
    </bean>
    <!--注入 Druid连接池-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="jdbc:mysql://127.0.0.1:3306/study"></property>
        <property name="name" value="root"></property>
        <property name="password" value="root" />
        <property name="maxActive" value="20" />  
        <property name="initialSize" value="1" />
        <property name="maxWait" value="60000" />
        <property name="minIdle" value="1" />
    </bean>
</beans>
```  
