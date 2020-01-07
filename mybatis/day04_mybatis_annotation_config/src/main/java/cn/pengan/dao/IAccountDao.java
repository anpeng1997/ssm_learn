package cn.pengan.dao;

import cn.pengan.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    @Select("select * from t_account")
    @Results(id = "accountMap",value = {
            @Result(id = true,column = "aid",property = "aid"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(column = "uid",property = "user",one = @One(select = "cn.pengan.dao.IUserDao.findUserById",fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    @Select("select * from t_account where uid = #{uid}")
    @ResultMap("accountMap")
    Account findAccountById(int uid);
}
