package com.lipop.micro.house.biz.dao;

import com.lipop.micro.house.common.model.Blog;
import com.lipop.micro.house.common.page.PageParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> selectBlogListForPage(@Param("blog") Blog query, @Param("pageParams") PageParams pageParams);

    Long selectBlogCount(Blog query);
}