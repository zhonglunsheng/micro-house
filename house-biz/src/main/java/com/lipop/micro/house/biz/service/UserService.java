package com.lipop.micro.house.biz.service;

import java.util.List;

import com.lipop.micro.house.biz.dao.UserMapper;
import com.lipop.micro.house.common.model.User;
import com.lipop.micro.house.common.utils.BeanHelper;
import com.lipop.micro.house.common.utils.HashUtils;
import com.lipop.micro.house.common.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
public class UserService {


  @Autowired
  private FileService fileService;

  @Autowired
  private MailService mailService;

  @Autowired
  private UserMapper userMapper;

  @Value("${file.prefix}")
  private String imgPrefix;


  public List<User> getUsers() {
    return userMapper.selectUserList();
  }

  /**
   * 1.插入数据库，非激活;密码加盐md5;保存头像文件到本地 2.生成key，绑定email 3.发送邮件给用户
   * 
   * @param account
   * @return
   */
  @Transactional(rollbackFor = Exception.class)
  public boolean addAccount(UserVo account) {
    account.setPasswd(HashUtils.encryPassword(account.getPasswd()));
    List<String> imgList = fileService.getImgPaths(Lists.newArrayList(account.getAvatarFile()));
    if (!imgList.isEmpty()) {
      account.setAvatar(imgList.get(0));
    }
    BeanHelper.setDefaultProp(account, User.class);
    BeanHelper.onInsert(account);
    account.setEnable(0);
    userMapper.insert(account);
    mailService.registerNotify(account.getEmail());
    return true;
  }

  public boolean enable(String key) {
    return mailService.enable(key);
  }

  /**
   * 用户名密码验证
   * 
   * @param username
   * @param password
   * @return
   */
  public User auth(String username, String password) {
    User user = new User();
    user.setEmail(username);
    user.setPasswd(HashUtils.encryPassword(password));
    user.setEnable(1);
    List<User> list = getUserByQuery(user);
    if (!list.isEmpty()) {
      return list.get(0);
    }
    return null;
  }


  public List<User> getUserByQuery(User user) {
    List<User> list = userMapper.selectUsersByQuery(user);
    for (User item:
         list) {
      item.setAvatar(imgPrefix + item.getAvatar());
    }
    return list;
  }

  public void updateUser(User updateUser, String email) {
    updateUser.setEmail(email);
    BeanHelper.onUpdate(updateUser);
    userMapper.updateByPrimaryKey(updateUser);
  }


  public User getUserById(Long id) {
    User queryUser = new User();
    queryUser.setId(id);
    List<User> users = getUserByQuery(queryUser);
    if (!users.isEmpty()) {
      return users.get(0);
    }
    return null;
  }

  public void resetNotify(String username) {
    mailService.resetNotify(username);
  }

  /**
   * 重置密码操作
   * @param key
   * @param password
   * @return
   */
  @Transactional(rollbackFor=Exception.class)
  public User reset(String key,String password){
    String email = getResetEmail(key);
    User updateUser = new User();
    updateUser.setEmail(email);
    updateUser.setPasswd(HashUtils.encryPassword(password));
    userMapper.updateByPrimaryKey(updateUser);
    mailService.invalidateRestKey(key);
    return getUserByEmail(email);
  }
  
  

  public User getUserByEmail(String email) {
    User queryUser = new User();
    queryUser.setEmail(email);
    List<User> users = getUserByQuery(queryUser);
    if (!users.isEmpty()) {
      return users.get(0);
    }
    return null;
  }

  public String getResetEmail(String key) {
    String email = "";
    try {
      email =  mailService.getResetEmail(key);
    } catch (Exception ignore) {
    }
    return email;
  }



}
