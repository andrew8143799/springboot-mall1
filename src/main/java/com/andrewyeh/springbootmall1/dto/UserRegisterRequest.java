package com.andrewyeh.springbootmall1.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRegisterRequest {

    //不可為null，也不可為全空白
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
