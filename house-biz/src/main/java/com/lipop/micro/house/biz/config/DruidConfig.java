package com.lipop.micro.house.biz.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhongls3
 * @create: 2018-08-22 15:50
 **/
@Configuration
public class DruidConfig {

    /**
     * datasource配置
     * @return
     */
    @ConfigurationProperties(prefix = "spring.druid")
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return dataSource;
    }

    /**
     * 慢SQL检测打印
     * @return
     */
    @Bean
    public Filter statFilter(){
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(1);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

}
