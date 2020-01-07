# spring中基于XML的AOP配置步骤

1. 导入解析切入点表达的jar包

    ```xml
     <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.5</version>
    </dependency>
    ```

2. 在配置文件中引入aop的声明：

    ```xml
    <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">
    </beans>
    ```

3. 把通知Bean也交给spring来管理

    ```xml
        <!--    配置ioc-->
        <bean id="userService" class="cn.pengan.service.impl.UserService"></bean>

        <bean id="log" class="cn.pengan.utils.Logg"></bean>

        <!--   配置AOP-->
        <aop:config>
        <aop:pointcut id="servicePointcut" expression="execution(* cn.pengan.service.impl.*.*(..))"/>
        <aop:aspect id="aspectLog" ref="log">
        <!--           前置通知-->
        <aop:before method="printBeforeLogg" pointcut-ref="servicePointcut"></aop:before>
        <!--            后置通知-->
        <aop:after method="printAfterLogg" pointcut-ref="servicePointcut"></aop:after>
        <!--            最终通知-->
        <aop:after-returning method="printAfterReturningLogg" pointcut-ref="servicePointcut"></aop:after-returning>
        <!--            异常通知-->
        <aop:after-throwing method="printAfterThrowingLogg" pointcut-ref="servicePointcut"></aop:after-throwing>
        </aop:aspect>
        </aop:config>
        </beans>
    ```

4. 使用aop:config标签表明开始AOP的配置

5. 使用aop:aspect标签表明配置切面
    * id属性：是给切面提供一个唯一标识
    * ref属性：是指定通知类bean的Id

6. 在aop:aspect标签的内部使用对应标签来配置通知的类型
    * aop:before：表示配置前置通知，在切入点方法执行之前执行
    * aop:after-returning :表示最终通知，在切入点方法正常执行之后值。它和异常通知永远只能执行一个
    * aop:after-throwing :表示异常通知，在切入点方法执行产生异常之后执行。它和后置通知永远只能执行一个
    * aop:after：表示配置后置 通知，无论切入点方法是否正常执行它都会在其后面执行
        * method属性：用于指定Logger类中哪个方法是通知

        * pointcut属性：用于指定切入点表达式，该表达式的含义指的是对业务层中哪些方法增强

    * aop:around :表示环绕通知，可以在该方法中调用服务方法，相当于动态代理模式中的功能，它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。

        ```xml
        <!--申明环绕通知-->
         <aop:around method="printAroundLogger" pointcut-ref="servicePointcut"></aop:around>
        ```

        ```java
            //此方法就相当于明确调用切入点方法。该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
            //我们可以直接在环绕方法中的参数中申明，通过该参数获得要执行的方法及方法的构造函数
            public Object printAroundLogger(ProceedingJoinPoint proceedingJoinPoint) {
            Object result = null;
            System.out.println("printAroundLogger.....");
            try {
                //得到方法执行所需的参数
                Object[] args = proceedingJoinPoint.getArgs();
                System.out.println("before......");
                ////明确调用业务层方法（切入点方法），假入这里不调用proceed的话，那我们的服务层方法则不会执行
                result = proceedingJoinPoint.proceed(args);
                System.out.println("after.......");
            } catch (Throwable throwable) {
                System.out.println("throwing.....");
                throwable.printStackTrace();
            } finally {
                System.out.println("after-returning.....");
            }
            return result;
        }
        ```

    * 切入点表达式的写法：
        * 关键字：execution(表达式)

        * 表达式：`访问修饰符  返回值  包名.包名.包名...类名.方法名(参数列表)`

        * 标准的表达式写法例子：`public void com.itheima.service.impl.AccountServiceImpl.saveAccount()`

        * 访问修饰符可以省略: `void com.itheima.service.impl.AccountServiceImpl.saveAccount()`

        * 返回值可以使用通配符，表示任意返回值:`* com.itheima.service.impl.AccountServiceImpl.saveAccount()`

        * 包名可以使用通配符，表示任意包。但是有几级包，就需要写几个* :`* *.*.*.*.AccountServiceImpl.saveAccount())`

        * 包名可以使用..表示当前包及其子包:`* *..AccountServiceImpl.saveAccount()`

        * 类名和方法名都可以使用*来实现通配:`* *..*.*()`

        * 参数列表：
            * 可以直接写数据类型：
                * 基本类型直接写名称  `int`

                * 引用类型写包名.类名的方式   `java.lang.String`

            * 可以使用通配符表示任意类型，但是必须有参数

            * 可以使用..表示有无参数均可，有参数可以是任意类型

        * 全通配写法：
            * `* *..*.*(..)`

        * 实际开发中切入点表达式的通常写法：
            * 切到业务层实现类下的所有方法
                * `* com.itheima.service.impl.*.*(..)`
