public class MyThread9 extends Thread {
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
            System.out.println(Thread.currentThread().getName() + " " + sum);
        }
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "运行时间= " + (end - begin));
    }
}
