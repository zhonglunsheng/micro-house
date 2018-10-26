package com.lsheng.house.biz.service;

import java.util.List;

import com.lsheng.house.biz.mapper.UserMapper;
import com.lsheng.house.common.model.User;
import com.lsheng.house.common.utils.BeanHelper;
import com.lsheng.house.common.utils.HashUtils;
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
        return userMapper.selectUsers();
    }

    /**
     * 1.插入数据库，非激活;密码加盐md5;保存头像文件到本地 2.生成key，绑定email 3.发送邮件给用户
     *
     * @param account
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addAccount(User account) {
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

    /**
     * 用户激活
     *
     * @param key
     * @return
     */
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

    /**
     * 获取用户信息
     *
     * @param user
     * @return
     */
    public List<User> getUserByQuery(User user) {
        List<User> list = userMapper.selectUsersByQuery(user);
        list.forEach(u -> {
            u.setAvatar(imgPrefix + u.getAvatar());
        });
        return list;
    }

    /**
     * 更新用户信息
     *
     * @param updateUser
     * @param email
     */
    public void updateUser(User updateUser, String email) {
        updateUser.setEmail(email);
        BeanHelper.onUpdate(updateUser);
        userMapper.update(updateUser);
    }

    /**
     * 根据用户id获取信息
     *
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        User queryUser = new User();
        queryUser.setId(id);
        List<User> users = getUserByQuery(queryUser);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 获取重置邮件
     *
     * @param username
     */
    public void resetNotify(String username) {
        mailService.resetNotify(username);
    }

    /**
     * 重置密码操作
     *
     * @param key
     * @param password
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public User reset(String key, String password) {
        String email = getResetEmail(key);
        User updateUser = new User();
        updateUser.setEmail(email);
        updateUser.setPasswd(HashUtils.encryPassword(password));
        userMapper.update(updateUser);
        mailService.invalidateRestKey(key);
        return getUserByEmail(email);
    }


    /**
     * 获取用户邮箱
     *
     * @param email
     * @return
     */
    public User getUserByEmail(String email) {
        User queryUser = new User();
        queryUser.setEmail(email);
        List<User> users = getUserByQuery(queryUser);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 获取重置token
     *
     * @param key
     * @return
     */
    public String getResetEmail(String key) {
        String email = "";
        try {
            email = mailService.getResetEmail(key);
        } catch (Exception ignore) {
        }
        return email;
    }


}
