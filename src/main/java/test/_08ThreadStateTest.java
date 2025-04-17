package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

public class _08ThreadStateTest {
    private static final Logger logger = MyLoggerFactory.getLogger("c." + _08ThreadStateTest.class.getSimpleName());

    public static void main(String[] args) {
        // NEW
        Thread t1 = new Thread(() -> logger.debug("running"), "t1");
        // RUNNABLE
        Thread t2 = new Thread(() -> {
            while (true) {

            }
        }, "t2");
        // TERMINATED
        Thread t3 = new Thread(() -> logger.debug("t3 running"), "t3");
        // TIMED_WAITING
        Thread t4 = new Thread(() -> {
            synchronized (_08ThreadStateTest.class) {
                try {
                    Thread.sleep(10_000_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t4");
        // WAITING
        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t5");
        // BLOCKED
        Thread t6 = new Thread(() -> {
            synchronized (_08ThreadStateTest.class) {
                try {
                    Thread.sleep(10_000_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t6");

        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.debug("t1 state: {}", t1.getState());
        logger.debug("t2 state: {}", t2.getState());
        logger.debug("t3 state: {}", t3.getState());
        logger.debug("t4 state: {}", t4.getState());
        logger.debug("t5 state: {}", t5.getState());
        logger.debug("t6 state: {}", t6.getState());
    }
}
