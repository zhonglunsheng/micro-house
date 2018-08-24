package com.lipop.micro.house.web.controller;

import java.util.List;

import com.lipop.micro.house.biz.service.BlogService;
import com.lipop.micro.house.biz.service.CommentService;
import com.lipop.micro.house.biz.service.RecommendService;
import com.lipop.micro.house.common.common.Constants;
import com.lipop.micro.house.common.model.Blog;
import com.lipop.micro.house.common.page.PageData;
import com.lipop.micro.house.common.page.PageParams;
import com.lipop.micro.house.common.vo.BlogVo;
import com.lipop.micro.house.common.vo.CommentVo;
import com.lipop.micro.house.common.vo.HouseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class BlogController {
  
  @Autowired
  private BlogService blogService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private RecommendService recommendService;
  
  @RequestMapping(value="blog/list",method={RequestMethod.POST,RequestMethod.GET})
  public String list(Integer pageSize, Integer pageNum, Blog query, ModelMap modelMap){
    PageData<BlogVo> ps = blogService.queryBlog(query, PageParams.build(pageSize, pageNum));
    List<HouseVo> houses =  recommendService.getHotHouse(Constants.RECOM_SIZE);
    modelMap.put("recomHouses", houses);
    modelMap.put("ps", ps);
    return "/blog/listing";
  }
  
  @RequestMapping(value="blog/detail",method={RequestMethod.POST,RequestMethod.GET})
  public String blogDetail(int id,ModelMap modelMap){
    Blog blog = blogService.queryOneBlog(id);
    List<CommentVo> comments = commentService.getBlogComments(id,8);
    List<HouseVo> houses =  recommendService.getHotHouse(Constants.RECOM_SIZE);
    modelMap.put("recomHouses", houses);
    modelMap.put("blog", blog);
    modelMap.put("commentList", comments);
    return "/blog/detail";
  }
}
