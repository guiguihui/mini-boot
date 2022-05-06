package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BillDao;
import com.example.demo.domain.Bill;
import com.example.demo.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl extends ServiceImpl<BillDao, Bill> implements IBillService {
    @Autowired
    private BillDao billDao;


    @Override
    public List<Bill> getByCarId(String carId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("Car_id", carId);
        List<Bill> billList = billDao.selectByMap(columnMap);

        
/*        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(carId);
        queryWrapper.eq("Car_id",carId);

        Bill bill = getOne(queryWrapper);*/
        return billList;
    }
}