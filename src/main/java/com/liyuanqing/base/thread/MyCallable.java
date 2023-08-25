package com.liyuanqing.base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("当前线程name = [" + Thread.currentThread().getName() + "]");
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i <= 99; i++) {
                Callable callable = new MyCallable();
                FutureTask futureTask = new FutureTask(callable);
                Thread thread = new Thread(futureTask);
                thread.start();
                System.out.println("当前线程名称:[" + Thread.currentThread().getName() + "]" + ",接收子线程名称[" + futureTask.get() + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
