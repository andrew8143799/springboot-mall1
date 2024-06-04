package com.andrewyeh.springbootmall1.controller;

import com.andrewyeh.springbootmall1.dto.UserLoginRequest;
import com.andrewyeh.springbootmall1.dto.UserRegisterRequest;
import com.andrewyeh.springbootmall1.model.User;
import com.andrewyeh.springbootmall1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity <User> getUserById(@PathVariable Integer userId){

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid
                                             UserRegisterRequest userRegisterRequest){

        Integer userId =  userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid
                                      UserLoginRequest userLoginRequest){

         User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}


