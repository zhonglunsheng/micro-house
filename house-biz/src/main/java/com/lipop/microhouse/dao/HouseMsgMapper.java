package com.lipop.microhouse.dao;

import com.lipop.microhouse.model.HouseMsg;

public interface HouseMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseMsg record);

    int insertSelective(HouseMsg record);

    HouseMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseMsg record);

    int updateByPrimaryKey(HouseMsg record);
}