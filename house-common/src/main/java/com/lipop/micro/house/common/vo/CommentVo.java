package com.lipop.micro.house.common.vo;

import com.lipop.micro.house.common.model.Comment;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author zhonglunsheng
 * @create 2018-08-23 19:30
 */
public class CommentVo extends Comment {
    private String avatar;
    private String userName;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
