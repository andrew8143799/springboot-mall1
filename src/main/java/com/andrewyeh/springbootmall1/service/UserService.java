package com.andrewyeh.springbootmall1.service;

import com.andrewyeh.springbootmall1.dto.UserLoginRequest;
import com.andrewyeh.springbootmall1.dto.UserRegisterRequest;
import com.andrewyeh.springbootmall1.model.User;

import java.util.List;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
