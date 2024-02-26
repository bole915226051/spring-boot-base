package com.liyuanqing.bean;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

public class UserService {
    private ThreadLocal<String> userName = new ThreadLocal<>();

    public String getUserName(String name) {
        try{
            userName.set("Hello Word:" + name);
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }

        return userName.get();
    }
}
