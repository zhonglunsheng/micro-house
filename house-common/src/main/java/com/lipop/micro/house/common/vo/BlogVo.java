package com.lipop.micro.house.common.vo;

import com.google.common.collect.Lists;
import com.lipop.micro.house.common.model.Blog;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author zhonglunsheng
 * @create 2018-08-23 18:56
 */
public class BlogVo extends Blog {

    private Integer id;
    private String  tags;
    private String  content;
    private String  title;
    private Date createTime;
    private String  digest;

    private List<String> tagList = Lists.newArrayList();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getDigest() {
        return digest;
    }
    public void setDigest(String digest) {
        this.digest = digest;
    }
    public List<String> getTagList() {
        return tagList;
    }
    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
