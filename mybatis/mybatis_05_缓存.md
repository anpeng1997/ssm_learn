# mybatis中的缓存

> 当我们重复调用一个查询方法时，并且参数一致时，mybaits会从缓存中读取数据，并不会去执行sql语句查询

## 一级缓存

> 原理：一级缓存是sqlSession范围的缓存，当调用SqlSession的修改、添加、删除、commint()、close()方法时，就会清空一级缓存。

## 二级缓存

> 原理：二级缓存是SqlSessionFactory范围的缓存，它缓存的不是对象，而是数据

1. 在mybatis文件中开启缓存

    ```xml
    <!--        开启二级缓存-->
     <settings>
        <setting name="cacheEnabled" value="true"/>
    </settings>
    ```

2. 在mapper配置文件中配置

    ```xml
    <!-- 表示使用缓存 -->
    <cache/>
    <!-- useCahe，表示该select使用缓存 -->
    <select id="findAll" resultMap="accountMap" useCache="true">
      select * from t_account
    </select>
    ```
