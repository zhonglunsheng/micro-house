package com.lipop.microhouse.interceptor;

import com.google.common.base.Joiner;
import com.lipop.microhouse.common.Constants;
import com.lipop.microhouse.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author zhonglunsheng
 * @create 2018-08-23 16:12
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Map<String,String[]> map = request.getParameterMap();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
            request.setAttribute(entry.getKey(), Joiner.on(",").join(entry.getValue()));
        }
        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/static") || requestUri.startsWith("/error")){
            return true;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER_ATTRIBUTE);
        if (user != null){
            UserContext.set(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.remove();
    }
}
