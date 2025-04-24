package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class _13ExerciseSell {
    public static final Logger logger = MyLoggerFactory.getLogger("c." + _13ExerciseSell.class.getSimpleName());
    // Random 为线程安全
    private static final Random random = new Random();

    public static void main(String[] args) {
        var count = 0;
        while (true) {
            var error = test();
            count++;
            if (error) {
                logger.error("程序有误, {}", count);
                break;
            }
        }
    }

    private static boolean test() {
        final var ticketCount = 10000;
        // var ticketWindow = new TicketWindow(ticketCount);
        var ticketWindow = new TicketWindow2(ticketCount);
        List<Thread> list = new ArrayList<>();
        // 用来存储买出去多少张票
        List<Integer> sellCount = new CopyOnWriteArrayList<>();
//        List<Integer> sellCount = new Vector<>();
//        List<Integer> sellCount = new LinkedList<>();
        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> {
                int count = ticketWindow.sell(randomAmount());
                sellCount.add(count);
            });
            list.add(t);
            t.start();
        }
        list.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }
        });
        // 卖出去的票求和
        int sum = sellCount.stream()
                .mapToInt(c -> c)
                .sum();
        logger.debug("卖票:{}", sum
        );
        // 剩余票数
        int count = ticketWindow.getCount();
        logger.debug("余票:{}", count);
        logger.debug("------------------------");
        return sum + count != ticketCount;
    }

    // 随机 1~5
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}

class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    // 解决方法一：加 synchronized 关键字
    public synchronized int sell(int amount) {
        if (this.count < amount) {
            return 0;
        }
        this.count -= amount;
        return amount;
    }
}

class TicketWindow2 {
    // 解决方法二：使用 AtomicInteger
    private AtomicInteger count;

    public TicketWindow2(int count) {
        this.count = new AtomicInteger(count);
    }

    public int sell(int amount) {
        if (this.count.get() < amount) {
            return 0;
        }
        count.addAndGet(-amount);
        return amount;
    }

    public int getCount() {
        return count.get();
    }
}