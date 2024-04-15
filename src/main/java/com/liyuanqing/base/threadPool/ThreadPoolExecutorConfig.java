package com.liyuanqing.base.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义创建线程池.
 * todo 线程池一些常见的问题?
 * 1.线程池都有哪些核心线程数?
 * 答:
 * 2.工作队列中常见的有界队列 和 无界队列的区别是什么?
 * 答:
 * 3.线程工程有什么作用?都有哪些线程?
 * 答:
 * 4.都有哪些拒绝策略?
 * 答:
 * 5.什么时候会开启最大线程数?
 * 答:当核心线程数的资源被占用 且 队列容量已经满了 再次进来的任务则会根据最大线程数创建线程.最大线程数创建的线程优先执行最后进来的任务而不是队列中等待的任务
 */
@Configuration
public class ThreadPoolExecutorConfig {
    @Bean("myPool")
    public Executor executor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //  核心线程数
        threadPoolTaskExecutor.setCorePoolSize(5);
        //  最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(10);
        //  最大等待时间
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        //  队列容量
        threadPoolTaskExecutor.setQueueCapacity(10);
        //  AbortPolicy：丢弃任务并抛出RejectedExecutionException异常。
        //  DiscardPolicy：丢弃任务，但是不抛出异常。可能导致无法发现系统的异常状态。
        //  DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务。
        //  CallerRunsPolicy：由调用线程处理该任务。
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutorConfig.AbortPolicy());
        threadPoolTaskExecutor.setThreadNamePrefix("Pool-Demo");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    public static class AbortPolicy implements RejectedExecutionHandler {

        public AbortPolicy() {
        }


        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("线程池最大线程数已经满了,队列容量也满了,任务不再接收.");
        }
    }

}
