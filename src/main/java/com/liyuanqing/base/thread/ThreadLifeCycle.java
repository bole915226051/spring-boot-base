package com.liyuanqing.base.thread;

/**
 * 用于监测一个线程的生命周期
 *
 * @author liyuanqing
 */
public class ThreadLifeCycle extends Thread {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        Thread.sleep(1000 * 5);
                        lock.wait();
                    }
                    synchronized (lock) {
                        Thread.sleep(1000 * 5);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        broadcastThread(thread);
        monitorThread(thread);
        thread.start();

    }

    private static void broadcastThread(Thread thread) {
        State state = null;
        new Thread() {
            @Override
            public void run() {
                State state = null;
                for (; ; ) {
                    //  线程状态不同时，播报线程
                    State currentState = thread.getState();
                    if (currentState != state) {
                        System.out.println("broadcastThread 线程监测 thread 状态：" + currentState);
                        state = currentState;
                    }
                }
            }
        }.start();
    }

    private static void monitorThread(Thread thread) {
        //  此线程的作用，用于释放waiting状态
        new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    if (thread.getState() == State.WAITING) {
                        synchronized (lock) {
                            try {
                                Thread.sleep(5000);
                                lock.notify();
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return;
                    }
                }
            }
        }.start();


    }

}
