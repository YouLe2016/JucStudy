package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的三种方法
 */
public class Test1 {
    private static final Logger log = MyLoggerFactory.getLogger("c.Test1");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        method1();
//        method2();
        method3();
    }

    /**
     * 创建线程的第一种方式，IDE已经不推荐了，推荐使用method2
     */
    private static void method1() {
        Thread t = new Thread() {
            @Override
            public void run() {
                log.debug("method1: running");
            }
        };
        t.start();
        log.debug("method1: running");
    }

    public static void method2() {
        var t = new Thread(() -> log.debug("method2: running"));
        t.start();
        log.debug("method2: running");
    }

    private static void method3() throws ExecutionException, InterruptedException {
        var task = new FutureTask<>(() -> {
            log.debug("method3 1");
            Thread.sleep(1000);
            log.debug("method3 2");
            return 1;
        });
        var t = new Thread(task, "t1");
        t.start();
        log.debug("method3 resultT1: {}", task.get());
    }
}
