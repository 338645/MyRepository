import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool_ThreadPoolMonitorTest {
    /**
     * 监控线程池
     */
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "编号的线程开始执行" + System.currentTimeMillis());
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + "编号的线程执行完毕" + System.currentTimeMillis());
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 30; i++) {
            Thread.sleep(500);
            executor.submit(r);
            System.out.println("------------------------------------------------------");
            System.out.println("当前线程池核心线程数：" + executor.getCorePoolSize());
            System.out.println("当前线程池最大线程数：" + executor.getMaximumPoolSize());
            System.out.println("当前线程池活动线程数：" + executor.getActiveCount());
            System.out.println("当前线程池大小：" + executor.getPoolSize());
            System.out.println("当前线程池共收到任务数：" + executor.getTaskCount());
            System.out.println("当前线程池完成任务数：" + executor.getCompletedTaskCount());
            System.out.println("当前线程池等待任务数：" + executor.getQueue().size());
            System.out.println("--------------------------------------------------------");
        }
        while (executor.getActiveCount() > 0) {
            System.out.println("------------------------------------------------------");
            System.out.println("当前线程池核心线程数：" + executor.getCorePoolSize());
            System.out.println("当前线程池最大线程数：" + executor.getMaximumPoolSize());
            System.out.println("当前线程池活动线程数：" + executor.getActiveCount());
            System.out.println("当前线程池大小：" + executor.getPoolSize());
            System.out.println("当前线程池共收到任务数：" + executor.getTaskCount());
            System.out.println("当前线程池完成任务数：" + executor.getCompletedTaskCount());
            System.out.println("当前线程池等待任务数：" + executor.getQueue().size());
            System.out.println("--------------------------------------------------------");
            Thread.sleep(1000);
        }
        if (executor.getActiveCount() == 0) {
            executor.shutdown();
        }
    }
}
