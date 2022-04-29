package com.example.demo.controller.dto;

import lombok.Data;

/**
 * 用来接收前端登录请求的类
 */
@Data
public class UserDTO {
    private Integer UserId;
    private String UserPassword;
    private  Integer IsAdmin;

    @Override
    public String toString() {
        return "UserDTO{" +
                "UserId=" + UserId +
                ", UserPassword='" + UserPassword + '\'' +
                ", IsAdmin=" + IsAdmin +
                '}';
    }

    public Integer getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        IsAdmin = isAdmin;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}
