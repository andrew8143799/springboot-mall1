package com.andrewyeh.springbootmall1.dao;

import com.andrewyeh.springbootmall1.dto.UserRegisterRequest;
import com.andrewyeh.springbootmall1.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);

}
