package com.lsheng.house.comment.mapper;

import java.util.List;

import com.lsheng.house.comment.model.Blog;
import com.lsheng.house.comment.model.LimitOffset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogMapper {
  
  public List<Blog> selectBlog(@Param("blog") Blog blog, @Param("pageParams") LimitOffset limitOffset);
  
  public Long selectBlogCount(Blog query);

}
