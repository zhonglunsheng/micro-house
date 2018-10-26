package com.lsheng.house.biz.mapper;

import java.util.List;

import com.lsheng.house.common.model.Blog;
import com.lsheng.house.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface BlogMapper {

   List<Blog> selectBlog(@Param("blog") Blog query, @Param("pageParams") PageParams params);

   Long selectBlogCount(Blog query);

}
