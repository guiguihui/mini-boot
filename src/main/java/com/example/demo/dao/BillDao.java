package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Bill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillDao extends BaseMapper<Bill> {
}
