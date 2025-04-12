package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

public class _04JoinTest {
    private static final Logger logger = MyLoggerFactory.getLogger("c._04JoinTest");
    private static int r = 0;
    private static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
        // 为什么使用join();
        // 多个结果();
        有时效的();
    }

    private static void 为什么使用join() throws InterruptedException {
        logger.debug("开始");
        Thread t1 = new Thread(() -> {
            logger.debug("开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            r = 10;
            logger.debug("结束");
        }, "t1");
        t1.start();
        // 加上这行代码即可
//        t1.join();
        logger.debug("结果为: {}", r);
        logger.debug("结束");
    }

    private static void 多个结果() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            logger.debug("开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            r = 10;
            logger.debug("结束");
        }, "t1");
        Thread t2 = new Thread(() -> {
            logger.debug("开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            r2 = 20;
            logger.debug("结束");
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        logger.debug("结果为: r1 = {}, r2 = {}, 耗时 = {}", r, r2, end - start);
        logger.debug("结束");
    }

    private static void 有时效的() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            logger.debug("开始");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            r = 20;
            logger.debug("结束");
        }, "t1");
        t1.start();
        t1.join(1500);
        long end = System.currentTimeMillis();
        logger.debug("结果为: r1 = {}, 耗时 = {}", r, end - start);
        logger.debug("结束");
    }
}
