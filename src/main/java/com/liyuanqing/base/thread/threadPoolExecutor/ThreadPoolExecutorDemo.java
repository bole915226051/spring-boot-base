package com.liyuanqing.base.thread.threadPoolExecutor;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 手动创建线程池并且验证
 *
 * @author liyuanqing
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
                new LinkedBlockingQueue<>(20),
                //  默认的线程工厂,
                Executors.defaultThreadFactory(),
                //  丢弃一条队列头部数据，然后再执行（应该插入队列里面去了）
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        for (; ; ) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("执行了队列中的任务");
                        Thread.sleep(1000 * 100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            try {
                Thread.sleep(1000 / 2);
                System.out.println("当前线程数：" + executor.getPoolSize()
                        + "当前队列数" + executor.getQueue().size()
                        + " >>> hello word!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
