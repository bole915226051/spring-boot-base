package com.liyuanqing.base.collection;


import java.util.HashMap;

/**
 * 通过预先设置HashMap的容量大小，来进行对比，插入1W条数据的次数
 * HashMap的扩容机制:当数组长度超过负载因子的倍数，则会扩容，扩容至2倍，数组初始的大小16，负载因子为0.75
 *
 * @author liyuanqing
 */
public class HashMapDemo {
    private static final int SIZE = 10000000;

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                HashMap map = new HashMap();
                for (int i = 0; i < SIZE; i++) {
                    map.put(String.valueOf(i), i);
                }
                System.out.println("未预先设置容量大小的插入时间:" + (System.currentTimeMillis() - start) + "ms");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                HashMap map = new HashMap(SIZE);
                for (int i = 0; i < SIZE; i++) {
                    map.put(String.valueOf(i), i);
                }
                System.out.println("预先设置容量大小的插入时间:" + (System.currentTimeMillis() - start) + "ms");
            }
        }.start();
    }
}
