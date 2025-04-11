package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

public class _02RunAndStartTest {
    private static final Logger logger = MyLoggerFactory.getLogger("c._02RunAndStartTest");

    /**
     * 输出结果：
     * 16:29:22 [t1] c._02RunAndStartTest - t1: running
     * 16:29:22 [main] c._02RunAndStartTest - t1: running
     */
    public static void main(String[] args) {
        var t1 = new Thread("t1") {
            @Override
            public void run() {
                logger.debug("t1: running");
            }
        };
        t1.start();
        t1.run();
    }
}
