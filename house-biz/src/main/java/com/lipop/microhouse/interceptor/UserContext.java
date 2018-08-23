package com.lipop.microhouse.interceptor;

import com.lipop.microhouse.model.User;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author zhonglunsheng
 * @create 2018-08-23 16:20
 */
public class UserContext {

    private static final ThreadLocal<User> USER_THREAD = new ThreadLocal();

    public static void set(User user){
        USER_THREAD.set(user);
    }

    public static void remove(){
        USER_THREAD.remove();
    }

    public static User getUser(){
        return USER_THREAD.get();
    }
}
