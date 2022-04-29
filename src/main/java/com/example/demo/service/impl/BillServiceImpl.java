package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BillDao;
import com.example.demo.domain.Bill;
import com.example.demo.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl extends ServiceImpl<BillDao, Bill> implements IBillService {
    @Autowired
    private BillDao billDao;
}