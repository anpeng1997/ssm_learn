package cn.pengan.dao;

import cn.pengan.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface IUserDao {

    @Select("select * from t_user where name = #{name} and password = #{password}")
    public User findUserByNamePwd(@Param("name") String name, @Param("password") String password);
}
