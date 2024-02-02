package com.liyuanqing.implementAndExtends;


public class C extends A {
    //  A 有 a()方法。
    //  B 实现了 B.a()方法。
    // 但是C只能继承一个父类,所以,只能打印一个并不会出现冲突。
    // 如果C想继承A实现B,那问题就简单了。系统默认C已经实现了B。就是A.a()方法。
}
