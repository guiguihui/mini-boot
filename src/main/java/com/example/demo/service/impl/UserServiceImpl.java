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
    public R login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserId",userDTO.getUserId());
        queryWrapper.eq("UserPassword",userDTO.getUserPassword());
        User user = getOne(queryWrapper);
        boolean flag  = user != null;
        return new R(flag,flag ? "登录成功^_^" : "登录失败-_-!");
    }

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
}