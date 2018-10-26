package com.lsheng.house;

/**
 * @author zhonglunsheng
 * @Description
 * @create 2018-10-26 14:30
 */
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@MapperScan("com.lsheng.house.biz.mapper")
@SpringBootApplication
public class HouseApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HouseApplication.class, args);
    }
}

