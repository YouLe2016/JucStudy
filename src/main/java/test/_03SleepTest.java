package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;
import java.util.concurrent.TimeUnit;

public class _03SleepTest {
    private static final Logger logger = MyLoggerFactory.getLogger("c._03SleepTest");


    public static void main(String[] args) throws InterruptedException {
//        线程状态转换();
//        打断睡眠的线程();

        // 睡眠1秒
        TimeUnit.SECONDS.sleep(1);
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    /**
     * 输出结果：
     * <p>
     * 09:55:42 [Thread-0] c._03SleepAndYieldTest - 调用sleep(3000) </br>
     * 09:55:43 [main] c._03SleepAndYieldTest - 调用t1.interrupt() </br>
     * 09:55:43 [Thread-0] c._03SleepAndYieldTest - t1被打断
     * @throws InterruptedException 打断异常
     */
    public static void 打断睡眠的线程() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            logger.debug("调用sleep(3000)");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.debug("t1被打断");
            }
        });
        t1.start();
        Thread.sleep(1000);
        logger.debug("调用t1.interrupt()");
        t1.interrupt();
    }

    /**
     * 输出结果：
     * <p>
     * 09:48:06 [main] c._03SleepAndYieldTest - t1的状态: RUNNABLE </br>
     * 09:48:07 [main] c._03SleepAndYieldTest - t1的状态2: TIMED_WAITING
     * @throws InterruptedException 打断异常
     */
    public static void 线程状态转换() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1");
        t1.start();
        logger.debug("t1的状态: {}", t1.getState());
        Thread.sleep(1000);
        logger.debug("t1的状态2: {}", t1.getState());
    }
}
