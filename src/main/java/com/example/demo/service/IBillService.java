package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.Bill;

import java.util.List;

public interface IBillService  extends IService<Bill> {
    List<Bill> getByCarId(String carId);
}
