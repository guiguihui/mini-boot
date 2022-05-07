package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.Reserve;

import java.util.List;

public interface IReserveService  extends IService<Reserve> {
    List<Reserve> getByCarId(String carId);
}
