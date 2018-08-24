package com.lipop.micro.house.biz.service;

import java.util.ArrayList;
import java.util.List;

import com.lipop.micro.house.biz.dao.CommentMapper;
import com.lipop.micro.house.common.common.Constants;
import com.lipop.micro.house.common.model.Comment;
import com.lipop.micro.house.common.model.User;
import com.lipop.micro.house.common.utils.BeanHelper;
import com.lipop.micro.house.common.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentService {
  
  @Autowired
  private CommentMapper commentMapper;
  
  @Autowired
  private UserService userService;

  public void addHouseComment(Long houseId, String content, Long userId) {
    addComment(houseId,null, content, userId,1);
  }

  @Transactional(rollbackFor=Exception.class)
  public void addComment(Long houseId,Integer blogId, String content, Long userId,int type) {
    Comment comment = new Comment();
    if (type == 1) {
      comment.setHouseId(houseId);
    }else {
      comment.setBlogId(blogId);
    }
    comment.setContent(content);
    comment.setUserId(userId);
    comment.setType(type);
    BeanHelper.onInsert(comment);
    BeanHelper.setDefaultProp(comment, Comment.class);
    commentMapper.insert(comment);
  }

  public List<CommentVo> getHouseComments(long houseId, int size) {
    List<Comment> commentList = commentMapper.selectHouseComments(houseId,size);
    return getCommentVos(commentList);
  }

  private List<CommentVo> getCommentVos(List<Comment> commentList) {
    List<CommentVo> comments = new ArrayList<>();
    commentList.forEach(comment -> {
      CommentVo commentVo = new CommentVo();
      BeanUtils.copyProperties(comment, commentVo);
      User user = userService.getUserById(comment.getUserId());
      commentVo.setAvatar(user.getAvatar());
      commentVo.setUserName(user.getName());
      comments.add(commentVo);
    });
    return comments;
  }

  public List<CommentVo> getBlogComments(long blogId, int size) {
    List<Comment> commentList = commentMapper.selectBlogComments(blogId,size);
    return getCommentVos(commentList);
  }

  public void addBlogComment(int blogId, String content, Long userId) {
    addComment(null,blogId, content, userId, Constants.COMMENT_BLOG_TYPE);
  }
  
  

}
