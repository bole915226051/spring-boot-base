package com.liyuanqing.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {
    /**
     * Bean的生命周期回调有几种？
     * 答：两种。一种是初始化生命周期回调，一种是销毁生命周期回调。
     * 有几种方式？
     * 答：有三种方式。
     * 1、注解
     * 2、实现接口
     * 3、指定方法
     * 优先级是：注解 > 实现接口 > 指定方法
     *
     * @return
     */
    @Bean(initMethod = "initl", destroyMethod = "destructionl")
    public UserService userService() {
        return new UserService();
    }
}
