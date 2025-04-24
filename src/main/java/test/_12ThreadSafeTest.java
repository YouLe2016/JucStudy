package test;

import java.util.ArrayList;
import java.util.List;

public class _12ThreadSafeTest {
    private static final int ThreadCount = 2;
    private static final int LoopCount = 200;

    public static void main(String[] args) {
//        final var test = new ThreadUnsafe();
//        final var test = new ThreadSafe();
        final var test = new ThreadUnSafeSub();
        for (int i = 1; i <= ThreadCount; i++) {
            new Thread(() -> test.method(LoopCount), "t" + i).start();
        }
    }
}

class ThreadUnsafe {
    private final List<String> list = new ArrayList<>();

    void method(int count) {
        for (int i = 0; i < count; i++) {
            testAdd();
            testRemove();
        }
    }

    private void testAdd() {
        list.add("1");
    }

    private void testRemove() {
        list.remove(0);
    }
}

// 局部变量引用，线程安全
class ThreadSafe {
    void method(int count) {
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            testAdd(list);
            testRemove(list);
        }
    }

    private void testAdd(List<String> list) {
        list.add("1");
    }

    protected void testRemove(List<String> list) {
        list.remove(0);
    }
}

// 暴漏引用导致线程不安全
class ThreadUnSafeSub extends ThreadSafe {
    @Override
    protected void testRemove(List<String> list) {
        // 每个线程都有不同的list
        new Thread(() -> list.remove(0)).start();
    }
}