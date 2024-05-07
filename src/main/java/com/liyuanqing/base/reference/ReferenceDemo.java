package com.liyuanqing.base.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 引用类型demo
 * <p>
 * GC的触发不是实时，而且堆内存到达阈值或者JVM空闲时触发。
 * 强引用永远不会被回收，哪怕触发OOM；
 * 软引用会在OOM前的GC会被回收。
 * 弱引用会在下次GC被回收。
 * 虚引用是队列Queue，将希望被回收的对象加入ReferenceQueue,GC触发会被回收。
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        String reference = "强引用";
        SoftReference<String> softReference = new SoftReference<String>(new String("软引用"));
        WeakReference<String> weakReference = new WeakReference<String>(new String("弱引用"));
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        PhantomReference<String> phantomReference = new PhantomReference<String>(new String("弱"), referenceQueue);
        System.gc();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("强引用 = " + reference);
        System.out.println("软引用 = " + softReference.get());
        System.out.println("弱引用 = " + weakReference.get());
        System.out.println("需引用 = " + phantomReference.get());
    }
}
