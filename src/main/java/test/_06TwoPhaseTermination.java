package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

public class _06TwoPhaseTermination {
    private static final Logger logger = MyLoggerFactory.getLogger("c._05InterruptTest");

    public static void main(String[] args) throws InterruptedException{
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();

        Thread.sleep(6000);
        twoPhaseTermination.stop();
    }

    /*
     输出结果：
    16:45:08.062 [Thread-0] c._05InterruptTest - 执行监控
    16:45:10.076 [Thread-0] c._05InterruptTest - 执行监控
    16:45:12.086 [Thread-0] c._05InterruptTest - 执行监控
    java.lang.InterruptedException: sleep interrupted
        at java.base/java.lang.Thread.sleep(Native Method)
        at test._06TwoPhaseTermination$TwoPhaseTermination.lambda$start$0(_06TwoPhaseTermination.java:33)
        at java.base/java.lang.Thread.run(Thread.java:840)
    16:45:14.066 [Thread-0] c._05InterruptTest - currentThread.isInterrupted() = false
    16:45:14.066 [Thread-0] c._05InterruptTest - 料理后事
     */
    private static class TwoPhaseTermination {
        private Thread monitor;

        public void start() {
            if (monitor != null) {
                stop();
            }
            monitor = new Thread(() -> {
                while (true) {
                    var currentThread = Thread.currentThread();
                    if (currentThread.isInterrupted()) {
                        logger.debug("料理后事");
                        break;
                    }
                    try {
                        // 情况1
                        logger.debug("执行监控");
                        // 情况2
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 输出结果：false
                        logger.debug("currentThread.isInterrupted() = " + currentThread.isInterrupted());
                        // 重置打断标记
                        currentThread.interrupt();
                    }
                }
            });
            monitor.start();
        }

        public void stop() {
            monitor.interrupt();
            monitor = null;
        }
    }
}
