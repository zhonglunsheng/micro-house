package com.lipop.micro.house.biz.dao;

import com.lipop.micro.house.common.model.House;
import com.lipop.micro.house.common.model.HouseMsg;
import com.lipop.micro.house.common.model.HouseUser;
import com.lipop.micro.house.common.page.PageParams;
import com.lipop.micro.house.common.vo.HouseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<House> selectPageHouses(@Param("house") HouseVo house, @Param("pageParams") PageParams pageParams);

    HouseUser selectHouseUser(Long userId, Long houseId, Integer integer);

    void insertHouseUser(HouseUser houseUser);

    HouseUser selectSaleHouseUser(@Param("houseId") Long houseId);

    void insertUserMsg(HouseMsg userMsg);

    void downHouse(Long id);

    void deleteHouseUser(Long id, Long userId, Integer value);
}