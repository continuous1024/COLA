package com.huan.distributed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Starter
 *
 * @author Frank Zhang
 */
@SpringBootApplication(scanBasePackages = {"com.huan.distributed","com.alibaba.cola"})
@MapperScan("com.huan.distributed.repository")
public class DistributedModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedModuleApplication.class, args);
    }
}
