package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Reserve;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReserveDao extends BaseMapper<Reserve> {
}
