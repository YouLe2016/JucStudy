package test;

import base.MyLoggerFactory;

import org.slf4j.Logger;

public class _10线程安全问题 {
    private static final Logger logger = MyLoggerFactory.getLogger("c." + _10线程安全问题.class.getSimpleName());

    private static int count = 0;
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException{
        // test1();
        // test2();
        test3();
    }

    private static void test3() throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                for (int j = 0; j < 10000; j++) {
                    room.increment();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                for (int j = 0; j < 10000; j++) {
                    room.decrement();
                }
            }
        });
        t2.start();
        t1.join();
        t2.join();
        logger.info("count={}", room.getCount());
    }

    /*
    输出结果：
    11:38:43.330 [main] c._10线程安全问题 - count=0
     */
    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock) {
                    count++;
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock) {
                    count--;
                }
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        logger.debug("count={}", count);
    }

    /*
    输出结果：
    // 并不是0
    12:21:26.196 [main] c._10线程安全问题 - count=2498
     */
    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count--;
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        logger.debug("count={}", count);
    }
}

class Room {
    private int count = 0;

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    public int getCount() {
        synchronized (this) {
            return count;
        }
    }
}
