package com.liyuanqing.base.thread;

public class MyRunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println("当前线程name = [" + Thread.currentThread().getName() + "]");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new MyRunnableImpl();
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
