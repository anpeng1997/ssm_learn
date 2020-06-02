# spring中声明式事务的配置

```xml
<context:property-placeholder location="classpath:jdbcConfig.properties"></context:property-placeholder>

<bean id="pooledDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${driver}" />
        <property name="jdbcUrl" value="${url}" />
        <property name="user" value="${username}" />
        <property name="password" value="${password}" />
    </bean>

<!--  申明式事务的配置-->
<!-- 这里要使用的是spring-jdbc中的事务管理 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="pooledDataSource"></property>
</bean>

<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <!-- 配置事务的属性 -->
    <tx:attributes>
        <tx:method name="*" read-only="false" isolation="DEFAULT"/>
            <!-- 以find开头的方法，设置为只读的，不会进行事务的操作 -->
        <tx:method name="find*" propagation="SUPPORTS" read-only="true"></tx:method>
    </tx:attributes>
</tx:advice>
    <!--配置aop，表示cn.pengan.service.impl下所有的方法都要进行事务的管理-->
<aop:config>
    <aop:advisor advice-ref="txAdvice" pointcut="execution(* cn.pengan.service.impl.*.*(..))"></aop:advisor>
</aop:config>

```

## `<tx:attributes>`中的`<tx:method>`的属性含义

* `isolation` : 用于指定的事务的隔离级别，默认值是DEFAULT,表示使用的是数据库的默认隔离级别（mysql 默认的隔离级别：REPEATABLE READ）

    1. 数据库事务并发问题

        (1). 脏读

        >例：  
        >1.Transaction01将某条记录的age值从20修改为30  
        >2.Transaction02读取了Transaction01更新过后的值（此时Transaction01还未提交）
        >3.Transaction01回滚，age的值回滚成20  
        >4.这时Transaction02读取到的30就是一个无效的值；（生产环境中一定不能出现脏读的情况）

        (2). 不可重复读

        >例:  
        >1.Transaction01读取了age为20  
        >2.Transaction02修改了age为30  
        >3.Transaction03再次读取了age,值为30（第一次和第二次读的值不一致）

        (3). 幻读

        >例：  
        >1.Transaction01在student表中读取到了一部分数据
        >2.Transaction02在student表中插入了一些数据
        >3.Transaction01再次读取student表，多出了一些了数据

    2. 数据库隔离级别

        > 数据库系统必须具有隔离并发运行各个事务的能力，使得它们不会相互影响，避免各种并发问题。一个事务与其它事务隔离的程度称为隔离级别。SQL标准中规定了多种事务隔离级别，不同隔离级别对应不同的干扰程度，隔离级别越高，数据一致性就越好，但并发性就越弱。

        (1). 读未提交：READ UNCOMMITTED

        >允许Transaction01读取Transaction02未提交的修改

        (2). 读已提交：READ COMMITTED

        >要求Transaction01只能读取Transaction02已提交的修改

        (3). 可重复读：REPEATABLE READ

        >确保Transaction01可以多次从一个字段中读取相对应的值，及Transaction01执行期间禁止其它事务对这个字段进行更新

        (4). 串行化：SERIALIZABLE

        > 确保Transaction01可以多次从一个表中读取到相同的行，在Transaction01执行期间，禁止其它事务对这个表进行添加、更新、删除操作。可以避免任何任何并发问题

    3.各个隔离级别是否能解决并发问题的能力如下表

    |     隔离级别       |  脏读 | 不可重复读  |  幻读 |
    |  ---------------  | ----  | :---------:| ----  |
    |  SERIALIZABLE     |  ⭕   |     ⭕    |  ⭕  |
    | REPEATABLE READ   |  ⭕   |     ⭕    |  ⭕  |
    | READ COMMITTED    |  ⭕   |     ❌    |  ❌  |
    | READ UNCOMMITTED  |  ❌   |     ❌    |  ❌  |

    4.各种数据库对事务隔离级别的支持

    |     隔离级别       | Oracle | MySQL  
    |  ---------------  |  ----  | :---------:|
    |   SERIALIZABLE    |  ⭕   |     ⭕    |
    | REPEATABLE READ   |  ❌   |     ⭕(默认)|
    | READ COMMITTED    |  ⭕   |     ⭕    |
    | READ UNCOMMITTED  |  ❌   |     ⭕    |

* `propagation` : 用于指定事务的传播行为。默认值为REQUIRED,表示一定会有事务，增删改的选择。查询方法就可以设置成SUPPORTS

    > 当事务方法被另外一个事务方法调用时，必须指定事务应该如何传播。`Spring`中定义了7种类传播行为

    |  事务传播行为类型   | 说明  |
    |  ----  | ----  |
    | REQUIRED       | 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择 |
    | SUPPORTS       | 支持当前事务，如果当前没有事务，就以非事务方式执行。 |
    | MANDATORY      | 使用当前的事务，如果当前没有事务，就抛出异常。 |
    | REQUIRES_NEW   | 新建事务，如果当前存在事务，把当前事务挂起。 |
    | NOT_SUPPORTED  | 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 |
    | NEVER          | 以非事务方式执行，如果当前存在事务，则抛出异常。 |
    | NESTED         | 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与REQUIRED类似的操作。 |

* `read-only` : 用于指定事务是否是只有读操作。默认为false,只有当一个方法内所有的操作都是查询操作时，该方法事务的`read-only`就可以设置成true,否则程序会抛出异常

* `time-out` : 用于指定事务的超时时间。默认值为-1，表示永不超时。如果设置了值，单位为秒

* `rollback-for` : 用于指定一个异常，当产生该异常时，业务回滚，产生其它异常时不回滚。没有默认值，表示任何方法都回滚

* `no-rollback-for` : 用于指定一个异常，当产生该异常时，业务不回滚，产生其它异常时回滚。没有默认值，表示任何方法都回滚
