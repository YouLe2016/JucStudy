package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.locks.LockSupport;

public class _07ParkTest {
    private static final Logger logger = MyLoggerFactory.getLogger("c._07ParkTest");

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            logger.debug("park...");
            LockSupport.park();
            logger.debug("park... 2");

            // isInterrupted(), 返回打断标记，这里为true
            // logger.debug("打断状态：{}", Thread.currentThread().isInterrupted());

            // Thread.interrupted()，返回打断标记并重置为false
            logger.debug("打断状态：{}",  Thread.interrupted());

            // 打断标记为真的时候，会让park方法返回（或者说失效），不会阻塞
            LockSupport.park();
            logger.debug("park... 3");
        }, "t1");
        t1.start();

        Thread.sleep(1000);
        // 打断处于park状态的线程
        t1.interrupt();
    }
}
