package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Parking;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkingDao extends BaseMapper<Parking> {
}
