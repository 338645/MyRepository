import java.util.Random;
import java.util.concurrent.*;

public class ThreadPool_RejectedExceptionHandlerTest {
    /**
     * 自定义线程池拒绝策略
     */
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt(10);
                System.out.println(Thread.currentThread().getId() + "--" + System.currentTimeMillis() + "开始睡眠" + num + "秒");
                try {
                    TimeUnit.SECONDS.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.
                SECONDS, new LinkedBlockingQueue<Runnable>(18), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r + " is discarding....");
            }
        });
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            threadPoolExecutor.submit(runnable);
        }
    }
}
