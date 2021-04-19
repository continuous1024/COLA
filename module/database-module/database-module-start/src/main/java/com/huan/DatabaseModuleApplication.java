package com.huan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Starter
 *
 * @author Frank Zhang
 */
@SpringBootApplication(scanBasePackages = {"com.huan","com.alibaba.cola"})
@MapperScan("com.huan.*.gatewayimpl.database")
public class DatabaseModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseModuleApplication.class, args);
    }
}
