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

* `isolation` : 用于指定的事务的隔离级别，默认值是DEFAULT,表示使用的是数据库的默认隔离级别

* `propagation` : 用于指定事务的传播行为。默认值为REQUIRED,表示一定会有事务，增删改的选择。查询方法就可以设置成SUPPORTS

* `read-only` : 用于指定事务是否只读。默认为false,只有查询方法可以设置成true

* `time-out` : 用于指定事务的超时时间。默认值为-1，表示永不超时。如果设置了值，单位为秒

* `rollback-for` : 用于指定一个异常，当产生该异常时，业务回滚，产生其它异常时不回滚。没有默认值，表示任何方法都回滚

* `no-rollback-for` : 用于指定一个异常，当产生该异常时，业务不回滚，产生其它异常时回滚。没有默认值，表示任何方法都回滚
