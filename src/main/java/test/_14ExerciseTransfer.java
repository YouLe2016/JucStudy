package test;

import base.MyLoggerFactory;
import org.slf4j.Logger;

import java.util.Random;

public class _14ExerciseTransfer {
    private static final Logger logger = MyLoggerFactory.getLogger("c." + _13ExerciseSell.class.getSimpleName());
    // Random 为线程安全
    private static final Random random = new Random();

    public static void main(String[] args) {
        Account a = new Account(1000);
        Account b = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAmount());
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        }, "t2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
        logger.info("a={}, b={}, total={}", a.getMoney(), b.getMoney(), a.getMoney() + b.getMoney());
    }

    // 随机 1~100
    private static int randomAmount() {
        return random.nextInt(100) + 1;
    }
}

class Account {
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void transfer(Account target, int amount) {
        synchronized (Account.class) {
            if (this.money >= amount) {
                this.money -= amount;
                target.money += amount;
            }
        }
    }
}
