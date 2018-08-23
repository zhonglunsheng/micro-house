package com.lipop.microhouse.model;

import java.util.Date;

public class Blog {
    private Integer id;

    private String tags;

    private Date createTime;

    private String title;

    private Integer cat;

    private String content;

    public Blog(Integer id, String tags, Date createTime, String title, Integer cat, String content) {
        this.id = id;
        this.tags = tags;
        this.createTime = createTime;
        this.title = title;
        this.cat = cat;
        this.content = content;
    }

    public Blog() {
        super();
    }

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
        this.tags = tags == null ? null : tags.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}