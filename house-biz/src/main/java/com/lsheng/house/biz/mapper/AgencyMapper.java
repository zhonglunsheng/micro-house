package com.lsheng.house.biz.mapper;

import java.util.List;

import com.lsheng.house.common.model.Agency;
import com.lsheng.house.common.model.User;
import com.lsheng.house.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface AgencyMapper {
  
    List<Agency> select(Agency agency);

    int insert(Agency agency);

    List<User>	selectAgent(@Param("user") User user, @Param("pageParams") PageParams pageParams);

	Long selectAgentCount(@Param("user") User user);

}
