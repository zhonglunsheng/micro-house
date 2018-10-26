package com.lsheng.house.biz.mapper;

import java.util.List;

import com.lsheng.house.common.model.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

	 List<User> selectUsers();
	
	 int insert(User account);

	 int delete(String email);

	 int update(User updateUser);

	 List<User> selectUsersByQuery(User user);
}
