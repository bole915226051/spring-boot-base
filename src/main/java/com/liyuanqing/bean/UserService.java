package com.liyuanqing.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserService implements InitializingBean, DisposableBean {
    private ThreadLocal<String> userName = new ThreadLocal<>();

    public String getUserName(String name) {
        try {
            userName.set("Hello Word:" + name);
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userName.get();
    }

    @PostConstruct
    public void init() {
        System.out.println("【注解】。初始化UserService.");
    }

    @PreDestroy
    public void destruction() {
        System.out.println("【注解】。销毁UserService.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【实现接口 InitializingBean】。初始化UserService.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【实现接口 DisposableBean】。销毁UserService.");
    }

    public void initl() throws Exception {
        System.out.println("【指定方法】。初始化UserService.");
    }

    public void destructionl() throws Exception {
        System.out.println("【指定方法】。销毁UserService.");
    }


}
