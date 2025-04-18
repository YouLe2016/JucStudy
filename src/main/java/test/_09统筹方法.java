package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

public class _09统筹方法 {
    private static final Logger logger = MyLoggerFactory.getLogger("c." + _09统筹方法.class.getSimpleName());

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            work("洗水壶", 1);
            work("烧开水", 15);
        }, "t1");
        Thread t2 = new Thread(() -> {
            work("洗茶壶", 2);
            work("洗茶杯", 5);
            work("拿茶叶", 10);
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        logger.info("泡茶喝");
    }

    private static void work(String workName, int time) {
        logger.info("{}, 耗时{}分钟", workName, time);
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }
}
