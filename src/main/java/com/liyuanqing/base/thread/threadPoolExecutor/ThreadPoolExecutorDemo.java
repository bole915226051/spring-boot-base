package com.liyuanqing.base.thread.threadPoolExecutor;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 手动创建线程池并且验证
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                //  核心线程数
                5,
                //  最大线程数
                10,
                //  空闲线程存活时间
                60,
                //  时间单位
                TimeUnit.SECONDS,
                //  队列
                new LinkedBlockingQueue<>(),
                //  默认的线程工厂,
                Executors.defaultThreadFactory(),
                //  默认的拒绝策略
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (; ; ) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " >>> hello word!");
                    try {
                        Thread.sleep(1000 * 100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
