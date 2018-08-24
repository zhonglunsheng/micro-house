package com.lipop.micro.house.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.lipop.micro.house.biz.service",
        "com.lipop.micro.house.web.controller","com.lipop.micro.house.biz.config"})
@MapperScan("com.lipop.micro.house.biz.dao")
public class MicroHourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroHourseApplication.class, args);
    }
}
