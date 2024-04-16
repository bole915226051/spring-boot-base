package com.liyuanqing.base.thread;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread")
public class ThreadController {
    /**
     * 该入口功能:压力测试 当前机器的最优线程数,用于设置线程池的核心线程数
     * 如何设置线程池的核心线程数和最大线程数?
     * 1.判断任务是CPU密集还是IO密集 理论:
     *  - CPU密集型,则为: 核心 = 当前机器CPU核数,最大 = 当前机器CPU核数 + 1
     *      - 避免上下文切换导致额外的开销
     *      - 为什么要加1,是为了应对线程执行时发生缺页中断或者其他异常导致的线程阻塞,我们就可以额外多设置一个线程,这样当某个线程暂时不需要CPU的时候,就可以替补线程继续利用CPU
     *  - IO密集型,则为: 核心 = 当前机器CPU核数 * 2,最大 = 当前机器CPU核数 * 4
     *      - 线程数和IO时间有关,和CPU核数无关,CPU是处于空闲状态,一般设置2倍
     *      - 网络IO,文件IO,比如:Mysql返回结果,等待响应时,线程是阻塞的不需要用到CPU,就可以充分压榨CPU资源
     *  - 混合任务: CPU核心数 * (1 + 线程等待时间 / 线程全量执行时间)
     *      = 这些都是理论上设置核心线程数的最优解,但是因为 实际的机器没有那么干净,会有其他线程占用CPU,所以最后就是进行压测,来计算最适合的数量.
     *      = 比如 设置当前应用的最大线程数,然后sleep住,通过压测,计算最优解.
     * @return
     */
    @RequestMapping(value = "/stressTestOptimalThreadNum",method = RequestMethod.GET)
    public String stressTestOptimalThreadNum(){
        try {
            System.out.println("当前电脑核心数:"+Runtime.getRuntime().availableProcessors());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }
}
