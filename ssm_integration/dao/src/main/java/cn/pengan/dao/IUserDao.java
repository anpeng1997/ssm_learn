package cn.pengan.dao;

import cn.pengan.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface IUserDao {

   @Insert("INSERT INTO t_user values(null,#{username},#{sex},#{age})")
    void saveUser(User user);

    @Select("SELECT * FROM t_user")
    List<User> findAll();
}
