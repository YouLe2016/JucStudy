public class JavaCallbackTest {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        Thread t1 = new Thread(() -> {
//            System.out.println("t1: i = " + i);
        });
        System.out.println("main: i = " + i);
        i = 2;
        System.out.println("main: i = " + i);
        Thread.sleep(1000L);
        t1.start();
    }
}
