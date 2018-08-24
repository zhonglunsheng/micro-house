package com.lipop.micro.house.biz.dao;

import com.lipop.micro.house.common.model.User;
import com.lipop.micro.house.common.page.PageParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUserAgentListForPage(@Param("user") User user, @Param("pageParams") PageParams pageParams);

    Long selectUserAgentCount(User user);

    List<User> selectUserList();

    List<User> selectUsersByQuery(User user);
}