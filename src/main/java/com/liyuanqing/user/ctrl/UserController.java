package com.liyuanqing.user.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testOne")
    public String testOne() {
        return "李源青是张孝涵的爹~";
    }


    @RequestMapping("/testTwo")
    public String testTwo() {
        return "李源青是张孝涵的爹2~";
    }

}