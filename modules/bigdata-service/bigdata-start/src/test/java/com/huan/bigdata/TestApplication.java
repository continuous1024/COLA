package com.huan.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class TestApplication {

    public static void main(String[] args) {
        //这里填的是TestApplication
        ApplicationContext context = SpringApplication.run(BigDataApplication.class, args);
    }
}
