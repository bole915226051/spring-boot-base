package com.liyuanqing.base.thread.threadPoolTaskExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义创建线程池.
 * todo 线程池一些常见的问题?
 * -.为什么要使用线程池?
 * 答:
 * 1.线程池都有哪些核心线程数?
 * 答:1、核心线程数、2、最大线程数、3、最大线程数空闲等待时间、4、时间单位、5、线程工厂、6、任务队列(有界队列/无界队列)、7、拒绝策略
 * 2.工作队列中常见的有界队列 和 无界队列的区别是什么?
 * 答: 在创建线程池的时候,可以选择有界队列和无界队列,比如使用LinkedBlockingQueue,如果不设置队列数量,则默认使用Integer的最大值,相关等于无界;
 *     有界队列就是在设置一个队列时,给定一个数量,在生产环境,一定要设置有界队列,否则会撑爆JVM的内存,导致OOM;
 * 3.线程工厂有什么作用?
 * 答:设置线程的名称?方便查看?
 * 4.什么时候会开启最大线程数?
 * 答:当核心线程数的资源被占用 且 队列容量已经满了 再次进来的任务则会根据最大线程数创建线程.最大线程数创建的线程优先执行最后进来的任务而不是队列中等待的任务
 * -.都有那些拒绝策略:
 * 答:：拒绝策略,表示当队列满了并且工作线程数量大于等于线程池的最大线程数时如何拒绝请求执行的策略。
     * AbortPolicy（直接抛出异常）、
     * CallerRunsPolicy（调用者运行）、
     * DiscardOldestPolicy（丢弃最老的任务）
     * DiscardPolicy（直接丢弃任务）等。
 */
@Configuration
public class ThreadPoolTaskExecutorConfig {
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
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolTaskExecutorConfig.AbortPolicy());
        threadPoolTaskExecutor.setThreadNamePrefix("Pool-Demo");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    public static class AbortPolicy implements RejectedExecutionHandler {

        public AbortPolicy() {
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("线程池最大线程数已经满了,队列容量也满了,任务不再接收.");
        }
    }

}
