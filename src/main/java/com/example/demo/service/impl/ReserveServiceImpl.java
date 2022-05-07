package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.ReserveDao;
import com.example.demo.domain.Reserve;
import com.example.demo.service.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveDao, Reserve> implements IReserveService {
    @Autowired
    private ReserveDao reserveDao;

    @Override
    public List<Reserve> getByCarId(String carId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("Car_id", carId);
        List<Reserve> reserveList = reserveDao.selectByMap(columnMap);
        return reserveList;
    }
}