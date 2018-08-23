package com.lipop.microhouse.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author zhonglunsheng
 * @create 2018-08-23 16:45
 */
@Component
public class AuthActionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (UserContext.getUser() == null){
           String errMsg = URLEncoder.encode("请先登录", "utf-8");
           String target = URLEncoder.encode(request.getRequestURL().toString(), "utf-8");
           if ("GET".equalsIgnoreCase(request.getMethod())){
               response.sendRedirect("/accounts/signin?errorMsg=" + errMsg + "&target=" + target);
           }else{
               response.sendRedirect("/accounts/signin?errorMsg=" + errMsg);
           }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
