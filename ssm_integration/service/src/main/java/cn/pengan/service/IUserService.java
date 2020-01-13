package cn.pengan.service;

import cn.pengan.domain.User;
import java.util.List;

public interface IUserService {
   public void saveUser(User user);

   public List<User> findAll();
}
