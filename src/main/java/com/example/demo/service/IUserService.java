package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.utils.R;
import com.example.demo.domain.User;

public interface IUserService extends IService<User> {

    boolean adminLogin(UserDTO userDTO);

    boolean login(Integer userId, String userPassword);

    User login2(Integer userId, String userPassword);
}
