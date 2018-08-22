package com.lipop.microhourse.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhongls3
 * @create: 2018-08-22 13:10
 **/
@Configuration
public class FilterConfigure {

    /**
     * 创建过滤器
     * 包装 配置拦截URL
     * @return
     */
    @Bean
    public FilterRegistrationBean requestFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestFilter());
        List<String> urlList = new ArrayList<>();
        urlList.add("*");
        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }
}
