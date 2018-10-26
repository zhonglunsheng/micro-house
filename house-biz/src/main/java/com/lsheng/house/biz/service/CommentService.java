package com.lsheng.house.biz.service;

import java.util.List;

import com.lsheng.house.biz.mapper.CommentMapper;
import com.lsheng.house.common.constants.CommonConstants;
import com.lsheng.house.common.model.Comment;
import com.lsheng.house.common.model.User;
import com.lsheng.house.common.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    /**
     * 添加房产评论
     *
     * @param houseId
     * @param content
     * @param userId
     */
    public void addHouseComment(Long houseId, String content, Long userId) {
        addComment(houseId, null, content, userId, 1);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addComment(Long houseId, Integer blogId, String content, Long userId, int type) {
        Comment comment = new Comment();
        if (type == 1) {
            comment.setHouseId(houseId);
        } else {
            comment.setBlogId(blogId);
        }
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setType(type);
        // 给对象comment 字段为null的赋值 避免空指针异常
        BeanHelper.onInsert(comment);
        BeanHelper.setDefaultProp(comment, Comment.class);
        commentMapper.insert(comment);
    }

    /**
     * 获取房产评论
     *
     * @param houseId
     * @param size
     * @return
     */
    public List<Comment> getHouseComments(long houseId, int size) {
        List<Comment> comments = commentMapper.selectComments(houseId, size);
        comments.forEach(comment -> {
            User user = userService.getUserById(comment.getUserId());
            comment.setAvatar(user.getAvatar());
            comment.setUserName(user.getName());
        });
        return comments;
    }

    /**
     * 获取博客评论
     *
     * @param blogId
     * @param size
     * @return
     */
    public List<Comment> getBlogComments(long blogId, int size) {
        List<Comment> comments = commentMapper.selectBlogComments(blogId, size);
        comments.forEach(comment -> {
            User user = userService.getUserById(comment.getUserId());
            comment.setUserName(user.getName());
            comment.setAvatar(user.getAvatar());
        });
        return comments;
    }

    /**
     * 添加博客评论
     *
     * @param blogId
     * @param content
     * @param userId
     */
    public void addBlogComment(int blogId, String content, Long userId) {
        addComment(null, blogId, content, userId, CommonConstants.COMMENT_BLOG_TYPE);
    }


}
