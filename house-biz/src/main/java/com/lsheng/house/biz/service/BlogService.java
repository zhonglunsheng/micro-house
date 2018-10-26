package com.lsheng.house.biz.service;

import java.util.List;

import com.lsheng.house.biz.mapper.BlogMapper;
import com.lsheng.house.common.model.Blog;
import com.lsheng.house.common.page.PageData;
import com.lsheng.house.common.page.PageParams;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    /**
     * 查询分页博客
     *
     * @param query
     * @param params
     * @return
     */
    public PageData<Blog> queryBlog(Blog query, PageParams params) {
        List<Blog> blogs = blogMapper.selectBlog(query, params);
        populate(blogs);
        Long count = blogMapper.selectBlogCount(query);
        return PageData.<Blog>buildPage(blogs, count, params.getPageSize(), params.getPageNum());
    }

    /**
     * 解析博客(标签、缩略信息)
     *
     * @param blogs
     */
    private void populate(List<Blog> blogs) {
        if (!blogs.isEmpty()) {
            blogs.stream().forEach(item -> {
                String stripped = Jsoup.parse(item.getContent()).text();
                item.setDigest(stripped.substring(0, Math.min(stripped.length(), 40)));
                String tags = item.getTags();
                item.getTagList().addAll(Lists.newArrayList(Splitter.on(",").split(tags)));
            });
        }
    }

    /**
     * 查询单条博客
     *
     * @param id
     * @return
     */
    public Blog queryOneBlog(int id) {
        Blog query = new Blog();
        query.setId(id);
        List<Blog> blogs = blogMapper.selectBlog(query, new PageParams(1, 1));
        if (!blogs.isEmpty()) {
            return blogs.get(0);
        }
        return null;
    }


}
