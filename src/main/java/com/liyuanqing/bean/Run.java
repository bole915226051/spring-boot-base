package com.liyuanqing.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring中Bean是线程安全的吗？
 * 单例Bean的情况
 *  - 如果在类中生命成员变量,并且有读写的操作,则线程是不安全的。
 *  - 但是有解决方案：
 *      1、设计为多例。
 *      2、将成员变量放在ThreadLocal中。
 *      3、同步锁,会影响应用的吞吐量。
 */
public class Run {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

        new Thread(()->{
            UserService userService = context.getBean(UserService.class);
            System.out.println(userService.getUserName("张三"));
        }).start();
        new Thread(()->{
            UserService userService = context.getBean(UserService.class);
            System.out.println(userService.getUserName("李四"));
        }).start();
    }
}
