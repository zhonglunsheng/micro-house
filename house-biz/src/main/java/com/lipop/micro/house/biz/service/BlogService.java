package com.lipop.micro.house.biz.service;

import java.util.ArrayList;
import java.util.List;

import com.lipop.micro.house.biz.dao.BlogMapper;
import com.lipop.micro.house.common.model.Blog;
import com.lipop.micro.house.common.page.PageData;
import com.lipop.micro.house.common.page.PageParams;
import com.lipop.micro.house.common.vo.BlogVo;
import org.jsoup.Jsoup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

@Service
public class BlogService {
  
  @Autowired
  private BlogMapper blogMapper;

  public PageData<BlogVo> queryBlog(Blog query, PageParams params) {
    List<Blog> blogs =  blogMapper.selectBlogListForPage(query,params);
    List<BlogVo> blogExtendVoList = populate(blogs);
    Long  count =  blogMapper.selectBlogCount(query);
    return PageData.buildPage(blogExtendVoList, count, params.getPageSize(), params.getPageNum());
  }

  private List<BlogVo> populate(List<Blog> blogs) {
    List<BlogVo> blogExtendVoList = new ArrayList<>();
    BlogVo blogExtendVo;
    for (Blog item :
            blogs) {
      blogExtendVo = new BlogVo();
      BeanUtils.copyProperties(item, blogExtendVo);
      String stripped = Jsoup.parse(item.getContent()).text();
      blogExtendVo.setDigest(stripped.substring(0, Math.min(stripped.length(), 40)));
      String tags = item.getTags();
      blogExtendVo.getTagList().addAll(Lists.newArrayList(Splitter.on(",").split(tags)));
      blogExtendVoList.add(blogExtendVo);
    }
    return blogExtendVoList;
  }

  public Blog queryOneBlog(int id) {
   return blogMapper.selectByPrimaryKey(id);
  }

}
