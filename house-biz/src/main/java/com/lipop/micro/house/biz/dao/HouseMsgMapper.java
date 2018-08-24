package com.lipop.micro.house.biz.dao;

import com.lipop.micro.house.common.model.HouseMsg;

public interface HouseMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseMsg record);

    int insertSelective(HouseMsg record);

    HouseMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseMsg record);

    int updateByPrimaryKey(HouseMsg record);
}