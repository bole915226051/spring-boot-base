package com.liyuanqing.base.thread;

/**
 * @author liyuanqing
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("当前线程name = [" + Thread.currentThread().getName() + "]");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 999; i++) {
            Thread thread = new MyThread();
            thread.start();
        }
    }

}
