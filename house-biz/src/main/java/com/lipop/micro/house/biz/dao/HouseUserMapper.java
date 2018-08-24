package com.lipop.micro.house.biz.dao;

import com.lipop.micro.house.common.model.HouseUser;

public interface HouseUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseUser record);

    int insertSelective(HouseUser record);

    HouseUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseUser record);

    int updateByPrimaryKey(HouseUser record);
}