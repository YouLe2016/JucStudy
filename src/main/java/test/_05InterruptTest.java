package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

public class _05InterruptTest {
    private static final Logger logger = MyLoggerFactory.getLogger("c._05InterruptTest");

    public static void main(String[] args) throws InterruptedException {
        // 打断睡眠的线程();
        打断正常线程();
    }

    /*
    输出结果：
    15:58:51.774 [Thread-0] c._05InterruptTest - 结束运行
    15:58:51.776 [main] c._05InterruptTest - t1是否被打断: true
     */
    private static void 打断正常线程() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    logger.debug("结束运行");
                    break;
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        t1.join();
        logger.debug("t1是否被打断: {}", t1.isInterrupted());
    }

    /*
     * 输出结果：
     15:50:55.446 [Thread-0] c._03SleepTest - 调用sleep(3000)</br>
     15:50:56.454 [main] c._03SleepTest - 调用t1.interrupt()
     15:50:56.454 [Thread-0] c._03SleepTest - t1被打断
     15:50:56.455 [Thread-0] c._03SleepTest - t1是否被打断: false
     15:50:56.456 [main] c._03SleepTest - t1是否被打断: false

     * 相关：_03SleepTest.打断睡眠的线程
     */
    public static void 打断睡眠的线程() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            logger.debug("调用sleep(3000)");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.debug("t1被打断");
                logger.debug("t1是否被打断: {}", Thread.currentThread().isInterrupted());
            }
        });
        t1.start();
        Thread.sleep(1000);
        logger.debug("调用t1.interrupt()");
        t1.interrupt();
        // 这里加，或者在t1线程的catch代码块中打印 isInterrupted()，结果为false
        // 如果不加join()的话会打印true, 关键在于时序（Timing）和检查的位置！
        t1.join();
        logger.debug("t1是否被打断: {}", t1.isInterrupted());
    }
}
