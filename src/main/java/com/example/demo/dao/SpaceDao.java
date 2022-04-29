package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Space;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpaceDao extends BaseMapper<Space> {
}
