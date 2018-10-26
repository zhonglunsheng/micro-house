package com.lsheng.house.biz.mapper;

import java.util.List;

import com.lsheng.house.common.model.*;
import com.lsheng.house.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface HouseMapper {

     public List<House>  selectPageHouses(@Param("house") House house, @Param("pageParams") PageParams pageParams);
    
     Long selectPageCount(@Param("house") House query);
	
	 int insert(User account);

	 List<Community> selectCommunity(Community community);

	 int insert(House house);

	 HouseUser selectHouseUser(@Param("userId") Long userId, @Param("id") Long houseId, @Param("type") Integer integer);
	
	 HouseUser selectSaleHouseUser(@Param("id") Long houseId);

	 int insertHouseUser(HouseUser houseUser);

	 int insertUserMsg(UserMsg userMsg);

	 int updateHouse(House updateHouse);
	
	  int downHouse(Long id);

	 int deleteHouseUser(@Param("id") Long id, @Param("userId") Long userId, @Param("type") Integer value);
	
}
