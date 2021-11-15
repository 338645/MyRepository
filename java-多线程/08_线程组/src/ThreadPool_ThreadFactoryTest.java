import java.util.Random;
import java.util.concurrent.*;

public class ThreadPool_ThreadFactoryTest {
    /**
     * 自定义线程工厂
     */
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt(10);
                System.out.println(Thread.currentThread().getId() + "--" + System.currentTimeMillis() + "开始睡眠：" + num + "秒");
                try {
                    TimeUnit.SECONDS.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + "--" + System.currentTimeMillis() + "睡眠结束。");
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                System.out.println("创建了线程：" + thread);
                thread.setDaemon(true);
                return thread;
            }
        });
        for (int i = 0; i < 5; i++) {
            //超过5个线程，执行默认拒绝策略AbortPolicy
            executor.submit(runnable);
        }
        Thread.sleep(10000);
    }
}
