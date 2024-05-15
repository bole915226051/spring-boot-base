package com.liyuanqing.designPatterns;

import lombok.Data;

/**
 * 单例设计模式：保证对象的实例，在全局中唯一
 * 使用场景：线程池、缓存、配置文件
 * Spring框架中Bean就是遵循了单例模式，保证了全局只有一份；
 */
@Data
public class SingletonDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    Singleton instance = Singleton.getInstance();
                    System.out.println("instance.hashCode() = " + instance.hashCode());
                }
            }.start();
        }


    }
}


class Singleton {
    public static Singleton instance = null;

    //  私有化 无参构造，目的就是为什么不允许外界可以new 对象；
    private Singleton() {

    }

    //  单例模式时，需要注意线程安全的问题
    public static synchronized Singleton getInstance() {
        if (null == instance) {
            instance = new Singleton();
        }
        return instance;
    }
}
