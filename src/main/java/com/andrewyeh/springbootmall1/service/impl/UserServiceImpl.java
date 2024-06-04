package com.andrewyeh.springbootmall1.service.impl;


import com.andrewyeh.springbootmall1.dao.UserDao;
import com.andrewyeh.springbootmall1.dto.UserLoginRequest;
import com.andrewyeh.springbootmall1.dto.UserRegisterRequest;
import com.andrewyeh.springbootmall1.model.User;
import com.andrewyeh.springbootmall1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);

    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest){

        //檢查Email是否已經註冊
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(user != null){

            log.warn("該 email{} 已被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //註冊新帳號
        return userDao.createUser(userRegisterRequest);

    }

    @Override
    public User login(UserLoginRequest userLoginRequest){

        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if(user == null){
            log.warn("該Email {} 尚未註冊", userLoginRequest.getEmail());

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //比較字串的質一定要用equals();
        if(user.getPassword().equals(userLoginRequest.getPassword())){
            return user;
        }else{

            log.warn("該帳號不存在或是密碼錯誤");
            throw new ResponseStatusException((HttpStatus.BAD_REQUEST));

        }

    }

}
