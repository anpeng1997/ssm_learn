# resultMap 配置

## 一对一

> 一个Account对应一个user,标签： `<association>`

```xml
    <mapper namespace="cn.pengan.dao.IAccountDao">
        <resultMap id="accountMap" type="account">
            <id property="aid" column="aid"></id>
            <result property="uid" column="uid"></result>
            <result property="money" column="money"></result>
            <!-- 一对多一关系映射-->
            <association property="user" column="uid">
                <id property="uid" column="uid"></id>
                <result property="username" column="username"></result>
                <result property="sex" column="sex"></result>
                <result property="age" column="age"></result>
            </association>
        </resultMap>
        <select id="findAll" resultMap="accountMap">
            select u.*,a.aid,a.money FROM t_user as u,t_account as a
            WHERE a.uid = u.uid
        </select>
    </mapper>
```

## 一对多

> 一个User对应多个Account,标签：`<collection>`

```xml
<mapper namespace="cn.pengan.dao.IUserDao">
    <resultMap id="userMap" type="user">
        <id property="uid" column="uid"></id>
        <result property="username" column="username"></result>
        <result property="sex" column="sex"></result>
        <result property="age" column="age"></result>
    <!-- 一对多关系映射-->
        <collection property="accounts" ofType="account">
            <id property="aid" column="aid"></id>
            <result property="uid" column="uid"></result>
            <result property="money" column="money"></result>
        </collection>
    </resultMap>
    <select id="findAll" resultMap="userMap">
        select * FROM t_user as u
        left join t_account as a
        on u.uid = a.uid
    </select>
</mapper>
```
