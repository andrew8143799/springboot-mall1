package com.andrewyeh.springbootmall1.service.impl;


import com.andrewyeh.springbootmall1.dao.UserDao;
import com.andrewyeh.springbootmall1.dto.UserRegisterRequest;
import com.andrewyeh.springbootmall1.model.User;
import com.andrewyeh.springbootmall1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);

    }

    public Integer register(UserRegisterRequest userRegisterRequest){

        return userDao.createUser(userRegisterRequest);

    }

}
