import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool_basicOperationTest {
    /**
     * 线程池的基本使用
     */
    static int commandNums = 0;

    public static void main(String[] args) throws InterruptedException {
        //创建有5个工作线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //向线程池中提交18个任务
        for (int i = 0; i < 18; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getId() + "线程任务正在执行，开始时间："
                        + System.currentTimeMillis());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + "线程任务执行完毕,结束时间："
                        + System.currentTimeMillis()
                        + "已执行任务：" + ++commandNums);
            });
        }
        //关闭线程池
        Thread.sleep(5000);
        System.out.println(commandNums);
        executorService.shutdown();
    }
}
