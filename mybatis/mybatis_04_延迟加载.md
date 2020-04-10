# mybatis 中的延迟加载

> 当查询对象有一对一或一对多关系时，我们有时候在查询时可能不需要那些关联的对象，这时候就要使用到mybaits中的延迟加载功能，就是当使用到该关联的对象时才进行查询
>> 原理：就是不使用sql中的join查询，不是一次性把所有数据查询出来，而是关联另外一个查询方法，当我们使用到关联的对象时，mybatis框架会执行该方法查询数据。

* 配置：在mybaits的配置文件中配置开启延迟加载

    ```xml
        <!-- 配置启用延迟加载-->
            <setting name="lazyLoadingEnabled" value="true"/>
        <!--设置按需加载-->
            <setting name="aggressiveLazyLoading" value="false"/>
    ```

* mapper配置文件

    ```xml
    <!-- 一对多一映射 ,设置延迟加载 -->
    <!-- 这里就不用再配置关联的对象字段的映射，而是用一个select标签指定一个方法 -->
    <!-- account的mapper配置文件中的配置，findUserById则是通过id来查找一个用户 -->
    <resultMap id="accountMap" type="account">
        <id property="aid" column="aid"></id>
        <result property="uid" column="uid"></result>
        <result property="money" column="money"></result>
        <!--一对多一映射 ,设置延迟加载配置-->
        <association property="user" column="uid" select="cn.pengan.dao.IUserDao.findUserById">
        </association>
    </resultMap>
    ```

    ```xml
    <!-- user的mapper配置文件中的配置 -->
     <resultMap id="userMap" type="user">
        <id property="uid" column="uid"></id>
        <result property="username" column="username"></result>
        <result property="sex" column="sex"></result>
        <result property="age" column="age"></result>
        <!--一对多关系映射-->
        <collection property="accounts" column="uid" ofType="account" select="cn.pengan.dao.IAccountDao.findAccountById">
        </collection>
    </resultMap>
    ```
