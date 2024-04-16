package com.liyuanqing.base.thread.threadPoolTaskExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadPoolExecutor")
public class ThreadPoolTaskExecutorController {
    @Autowired
    private ThreadPoolTaskService threadPoolTaskService;

    @RequestMapping(value = "/ctrl", method = RequestMethod.GET)
    public String testOne(@RequestParam("param") String param) {
        for (int i = 0; i < 5; i++) {
            threadPoolTaskService.demoMyPool(param);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
        return param;
    }
}
