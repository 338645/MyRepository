import java.util.concurrent.*;

public class ThreadPool_ThreadPoolExtraTest {
    /**
     * 线程池的扩展
     */
    private static class MyTask implements Runnable {
        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "任务正在被线程" + Thread.currentThread().getId() + "执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "任务执行完毕。");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        //定义扩展线程池，重写afterExecute()和beforeExecute()方法
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t.getId() + "准备执行" + r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(r + "执行完毕");
            }

            //线程池退出
            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        for (int i = 0; i < 5; i++) {
            MyTask myTask = new MyTask("task-" + i);
            executorService.submit(myTask);
        }

        Thread.sleep(3000);
        executorService.shutdown();//等待执行完已接收的线程后关闭线程池，不再接受新的线程。
    }
}
