package com.lipop.micro.house.biz.dao;

import com.lipop.micro.house.common.model.Community;
import com.lipop.micro.house.common.vo.HouseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

    List<Community> selectCommunityList(Community community);

    Long selectCommunityPageCount(@Param("house") HouseVo house);
}