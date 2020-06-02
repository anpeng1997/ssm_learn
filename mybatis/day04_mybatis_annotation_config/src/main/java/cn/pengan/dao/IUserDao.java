package cn.pengan.dao;

import cn.pengan.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)
public interface IUserDao {
    @Select("select * from t_user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "uid", property = "uid"),
            @Result(column = "username", property = "username"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "age", property = "age"),
            //配置一对多，并设置延迟加载
            @Result(column = "uid", property = "accounts",
                    many = @Many(select = "cn.pengan.dao.IAccountDao.findAccountById", fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    List<User> findAllUserAndRole();

    @Select("select * from t_user where uid = #{uid}")
    @ResultMap("userMap")
    User findUserById(int uid);
}
