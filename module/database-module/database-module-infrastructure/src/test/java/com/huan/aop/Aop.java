package com.huan.aop;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Aop {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // 创建字节码增强器
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(Student.class);
        // 设置回调函数
        enhancer.setCallback(new TargetInterceptor());
        // 创建代理类
        Student student = (Student)enhancer.create();
        student.eat("张三");

        Method eat = student.getClass().getMethod("eat", String.class);
        eat.setAccessible(true);
        eat.invoke(student,"李四");

        // 由CGLIB创建的代理类，不会包含父类中的私有方法
        try {
            Method sleep = student.getClass().getMethod("sleep", String.class);
            sleep.setAccessible(true);
            sleep.invoke(student,"李四");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------");

        eat = Student.class.getDeclaredMethod("eat", String.class);
        eat.setAccessible(true);
        eat.invoke(student,"王二杆子");

        System.out.println("----------------------");
        Method sleep = Student.class.getDeclaredMethod("sleep", String.class);
        sleep.setAccessible(true);
        sleep.invoke(student,"王二杆子");
    }
}
