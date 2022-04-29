package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.SpaceDao;
import com.example.demo.domain.Space;
import com.example.demo.service.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl extends ServiceImpl<SpaceDao, Space> implements ISpaceService {
    @Autowired
    private SpaceDao spaceDao;
}