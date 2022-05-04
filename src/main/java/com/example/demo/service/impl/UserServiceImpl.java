package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.utils.R;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    private UserDao userDao;



    @Override
    public boolean adminLogin(UserDTO userDTO) {
        /*queryWrapper获取userDTO 的ID和密码，在数据库里进行查找*/
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("User_id",userDTO.getUserId());
        queryWrapper.eq("User_password",userDTO.getUserPassword());
        User admin = getOne(queryWrapper);
        boolean flag  = (admin != null);
        return flag;
    }

    @Override
    public boolean login(Integer userId, String userPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("User_id", userId);
        queryWrapper.eq("User_password", userPassword);
        User admin = getOne(queryWrapper);
        boolean flag = (admin != null);
        return flag;
    }

    @Override
    public User login2(Integer userId, String userPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("User_id", userId);
        queryWrapper.eq("User_password", userPassword);
        User user = getOne(queryWrapper);
        return user;
    }
}