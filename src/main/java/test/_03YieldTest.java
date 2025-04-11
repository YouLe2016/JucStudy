package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

public class _03YieldTest {
    private static final Logger logger = MyLoggerFactory.getLogger("c._03YieldTest");

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            var count = 0;
            while (true) {
                System.out.println("----> t1: " + count++);
            }
        });
        Thread t2 = new Thread(() -> {
            var count = 0;
            while (true) {
                // 测试礼让。相对来说更明显
//                Thread.yield();
                System.out.println("             ----> t2: " + count++);
            }
        });
        // 测试优先级
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }
}
