package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;


@Data
public class User {
    @TableId
    private Integer UserId;
    private String UserName;
    private String UserPassword;
    private String CarId;
    private int UserGender;
    private String UserPhone;
    private Integer IsAdmin;
    //逻辑删除 0表未删除 1表已删除
    @TableLogic(value = "0",delval = "1")
    private Integer IsDeleted;

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                ", CarId='" + CarId + '\'' +
                ", UserGender=" + UserGender +
                ", UserPhone='" + UserPhone + '\'' +
                ", IsAdmin=" + IsAdmin +
                ", IsDeleted=" + IsDeleted +
                '}';
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getCarId() {
        return CarId;
    }

    public void setCarId(String carId) {
        CarId = carId;
    }

    public int getUserGender() {
        return UserGender;
    }

    public void setUserGender(int userGender) {
        UserGender = userGender;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public Integer getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        IsAdmin = isAdmin;
    }

    public Integer getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        IsDeleted = isDeleted;
    }

}

