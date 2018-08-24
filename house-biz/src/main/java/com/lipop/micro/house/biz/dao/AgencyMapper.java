package com.lipop.micro.house.biz.dao;

import com.lipop.micro.house.common.model.Agency;

import java.util.List;

public interface AgencyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Agency record);

    int insertSelective(Agency record);

    Agency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Agency record);

    int updateByPrimaryKey(Agency record);

    List<Agency> selectAgencyList(Agency agency);
}