import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPool_ScheduledExecutorTest {
    /**
     * 线程池的计划任务演示
     */
    public static void main(String[] args) {
        //创建一个有10个线程的有调度功能的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "-" + System.currentTimeMillis());
            }
        };
        //schedule(Runnable command, long delay, TimeUnit unit)
        System.out.println(Thread.currentThread().getId() + "-" + System.currentTimeMillis());
        //scheduledExecutorService.schedule(r, 2, TimeUnit.SECONDS);
        //scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)是在上次任务结束后在【固定延迟】后再次执行该任务。
/*        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getId() + "-以固定频率执行任务-" + System.currentTimeMillis());
            try {
                //如果任务执行时长超过了执行周期，则任务执行完毕之后立即开启下一个任务
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3, 2, TimeUnit.SECONDS)*/;
        //scheduleWithFixedDelay(Runnable command, long initialDelay, long period, TimeUnit unit)总是在上次任务执行完毕后延迟【固定时间】执行下一个任务。
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getId() + "-以固定频率执行任务-" + System.currentTimeMillis());
            try {
                //如果任务执行时长超过了执行周期，则任务执行完毕之后立即开启下一个任务
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3, 2, TimeUnit.SECONDS);
    }
}
