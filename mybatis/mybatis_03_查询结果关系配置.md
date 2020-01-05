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

## 多对多

> 一个user有多个role，一个role有多个user，在两个bean类中
分别添加对方的list集合对象,标签:`<collection>`

```xml
<!-- user的map文件配置 -->
   <resultMap id="userAndRoleMap" type="user">
        <id column="uid" property="uid"></id>
        <result column="username" property="username"></result>
        <result column="sex" property="sex"></result>
        <result column="age" property="age"></result>
           <collection property="roles" ofType="role">
               <id column="rid" property="rid"></id>
               <result column="rolename" property="roleName"></result>
               <result column="description" property="description"></result>
           </collection>
    </resultMap>
    <!--    多对多，一个用户有多个角色-->
    <select id="findAllUserAndRole" resultMap="userAndRoleMap">
        select u.*,r.* from t_user_role as ur
        left join t_user u
        on ur.user_id = u.uid
        left join t_role r
        on ur.role_id = r.rid
    </select>
```

```xml
    <!-- role的map配置文件配置 -->
    <resultMap id="roleAndUserMap" type="Role">
        <id column="rid" property="rid"></id>
        <result column="rolename" property="roleName"></result>
        <result column="description" property="description"></result>
        <collection property="users" ofType="User">
            <id column="uid" property="uid"></id>
            <result column="username" property="username"></result>
            <result column="sex" property="sex"></result>
            <result column="age" property="age"/>
        </collection>
    </resultMap>
    <!-- 多对多，一个角色有多个用户 -->
    <select id="findAllAndUser" resultMap="roleAndUserMap">
        select r.*,u.* from t_user_role as ur
        left join t_role as r
        on ur.role_id=r.rid
        left join t_user as u
        on ur.user_id = u.uid
    </select>
```
