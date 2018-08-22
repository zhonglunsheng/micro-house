package com.lipop.microhouse.controller;

import com.lipop.microhouse.dao.UserMapper;
import com.lipop.microhouse.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhongls3
 * @create: 2018-08-22 14:36
 **/
@RestController
public class MyTestController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/myteset")
    public User test(){
        return userMapper.selectByPrimaryKey(10L);
    }
}
