package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.ParkingDao;
import com.example.demo.domain.Parking;
import com.example.demo.service.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingServiceImpl extends ServiceImpl<ParkingDao, Parking> implements IParkingService {
   @Autowired
   private ParkingDao parkingDao;
}