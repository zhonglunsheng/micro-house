package com.lipop.microhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.lipop.microhouse.dao")
@SpringBootApplication
public class MicroHourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroHourseApplication.class, args);
    }
}
