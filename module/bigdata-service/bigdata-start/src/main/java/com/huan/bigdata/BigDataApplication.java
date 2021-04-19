package com.huan.bigdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Starter
 *
 *
 * @author Frank Zhang
 */
@SpringBootApplication
@MapperScan("com.huan.bigdata.repository")
public class BigDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigDataApplication.class, args);
    }
}
