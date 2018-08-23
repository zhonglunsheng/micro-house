package com.lipop.microhouse.dao;

import com.lipop.microhouse.model.HouseUser;

public interface HouseUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseUser record);

    int insertSelective(HouseUser record);

    HouseUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseUser record);

    int updateByPrimaryKey(HouseUser record);
}