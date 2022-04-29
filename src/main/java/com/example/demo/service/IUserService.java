package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.utils.R;
import com.example.demo.domain.User;

public interface IUserService extends IService<User> {
    R login(UserDTO userDTO);

    boolean adminLogin(UserDTO userDTO);
}
