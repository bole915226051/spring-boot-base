package com.liyuanqing.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {
    @Bean
    public UserService userService(){
        return new UserService();
    }
}
