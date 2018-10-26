package com.lsheng.house.web;

/**
 * @author zhonglunsheng
 * @Description
 * @create 2018-10-26 14:30
 */
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan(basePackages = "com.lsheng.house.biz.mapper")
@SpringBootApplication(scanBasePackages = {"com.lsheng.house.biz", "com.lsheng.house.web"})
public class HouseApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HouseApplication.class, args);
    }
}

