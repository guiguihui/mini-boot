package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.ReserveDao;
import com.example.demo.domain.Reserve;
import com.example.demo.service.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveDao, Reserve> implements IReserveService {
    @Autowired
    private ReserveDao reserveDao;
}