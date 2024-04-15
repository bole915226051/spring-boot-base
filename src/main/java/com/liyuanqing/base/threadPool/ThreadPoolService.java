package com.liyuanqing.base.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ThreadPoolService {
    @Async("myPool")
    public void demoMyPool(String param) {
        try {
            log.info("当前线程名称:[{}],param=[{}]", Thread.currentThread().getName(), param);
            Thread.sleep(1000 * 60);
        } catch (Exception e) {
            log.error("当前线程名称:[{}]", Thread.currentThread().getName(), e);
        }

    }
}
