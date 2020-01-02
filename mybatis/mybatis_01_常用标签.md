# 常用标签

## mybatis 配置文件中

* `<properties></properties>`
    > 用于读取properties配置文件中的配置

    ```xml
    <properties resource="jdbcConfig.properties"></properties>
    <!-- 再配置文件中使用 ${key} 的方式获取获取-->
    ```
  
* `<typeAliases></typeAliases>`
    >用于配置别名，指定后该包下所有的实体类都会注册别名，类名就是别名，不区分大小写

    ```xml
    <typeAliases>
        <package name="cn.pengan"/>
    </typeAliases>
    ```

* `<package></package>`
    >这里说的是`<mappers>`标签下的`<package>`：指定dao接口包，就不用指定resource或class属性了

    ```xml
        <mappers>
            <package name="cn.pengan.dao"/>
        </mappers>
    ```

## mapper配置文件中sql常用标签

* `if`
    >用来判断是否添加后续的sql语句

    ```xml
    <if test="name != null">
        and name = #{name}
    </if>
    ```

* `where`
    >用来添加where条件，使用了where标签我们就不用再使用`where 1=1`来拼接

    ```xml
     <where>
        and name = #{name}
    </where>
    ```

* `foreach`
    >用来添加`in(,,,,)`条件

    ```xml
    <where>
        <foreach collection="ids" open="id in(" close=")" item="id" separator=",">
            #{id}
        </foreach>
    </where>
    ```
