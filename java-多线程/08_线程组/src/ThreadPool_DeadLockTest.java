import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool_DeadLockTest {
    /**
     * 线程池死锁演示
     */
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), Executors.defaultThreadFactory());
        AtomicBoolean flag = new AtomicBoolean(false);
        Runnable taskB = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "正在执行taskB");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag.set(true);
                System.out.println(Thread.currentThread().getId() + "执行完毕taskB");
            }
        };
        Runnable taskA = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "正在执行taskA");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!flag.get()) {
                }
                System.out.println(Thread.currentThread().getId() + "执行完毕taskA");
            }
        };
        threadPoolExecutor.execute(taskA);
        threadPoolExecutor.execute(taskB);
    }
}
