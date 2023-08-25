package com.liyuanqing.user.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testOne")
    public String testOne() {
        return "第一个入口";
    }


    @RequestMapping("/testTwo")
    public String testTwo() {
        return "出现分支问题.";
    }

}