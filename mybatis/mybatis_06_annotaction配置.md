# mybatis 注解

> 当一个dao接口类使用了注解时，那么就不能再使用mapper.xml配置文件进行对该接口配置了(在resource包下也不能出现同包名同类名的xml配置文件,因为mybatis不知道去使用那个进行配置)，反之同理。因为同一个接口只能使用一种方法进行配置，否则会出现异常

## 常用注解

* `@Select()`

    ```java
    @Select("select * from t_user")
     List<User> findAll();
    ```

* `@Update()`

* `@Insert()`

* `@Delete()`

## 字段映射注解

* `@Results`

    ```java
     //该id是作为该映射的唯一标识，在别的方法上可以使用 @ResultMap()进行引用
     @Results(id = "userMap",value = {
            @Result(id = true,column = "uid",property = "uid"),
            @Result(column = "username",property = "username"),
            @Result(column ="sex",property = "sex"),
            @Result(column ="age",property = "age"),
    })
    ```

## 一对一及立即加载

* `@One`

```java
    @Select("select * from t_account")
    @Results(id = "accountMap",value = {
            @Result(id = true,column = "aid",property = "aid"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            //一对一
            @Result(column = "uid",property = "user",one = @One(select = "cn.pengan.dao.IUserDao.findUserById",fetchType = FetchType.EAGER))
    })
    List<Account> findAll();
```

## 一对多及延迟加载

* `@Many`

```java
    @Select("select * from t_user")
    @Results(id = "userMap",value = {
            @Result(id = true,column = "uid",property = "uid"),
            @Result(column = "username",property = "username"),
            @Result(column ="sex",property = "sex"),
            @Result(column ="age",property = "age"),
            //配置一对多，并设置延迟加载
            @Result(column = "uid",property = "accounts",
                    many = @Many(select = "cn.pengan.dao.IAccountDao.findAccountById",fetchType = FetchType.LAZY))
    })
    List<User> findAll();
```

## 二级缓存

>使用`@CacheNamespace(blocking = true)`开启二级缓存

```java
    @CacheNamespace(blocking = true)
    public interface IUserDao {

    }
```
