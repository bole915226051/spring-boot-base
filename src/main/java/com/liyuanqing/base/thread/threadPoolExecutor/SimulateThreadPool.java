package com.liyuanqing.base.thread.threadPoolExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟一个线程池
 *
 * @author liyuanqing
 */
public class SimulateThreadPool {
    // 阻塞队列
    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
    private static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) {

        Thread producer = new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        int value = integer.incrementAndGet();
                        if (queue.offer(String.valueOf(value))) {
                            System.out.println("生产者生产了：" + value);
                        } else {
                            //  设置拒绝策略
                            throw new RuntimeException("消息队列已经满了," + value + "添加失败");
                        }
                        Thread.sleep(1000);
                    } catch (RuntimeException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread customer1 = new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        System.out.println("消费者1成功消费：" + queue.take());
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread customer2 = new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        System.out.println("消费者2成功消费：" + queue.take());
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        producer.start();
        customer1.start();
        customer2.start();

    }
}
