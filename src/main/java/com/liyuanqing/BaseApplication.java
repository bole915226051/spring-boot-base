package com.liyuanqing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseApplication {
    private static final int sss;

    static {
        sss = 100;
    }


    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

}
