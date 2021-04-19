package com.huan.aop;

public class Student {
    public void eat(String name) {
        System.out.println(name + "正在吃饭...");
    }

    private void sleep(String name){
        System.out.println(name + "正在偷偷睡觉...");
    }
}
