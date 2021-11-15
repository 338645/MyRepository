import java.util.concurrent.*;

public class ThreadPool_ExceptionExecuteTest {
    /**
     * 线程池中的异常处理
     * 线程池可能会吃掉程序中的异常
     */
    //解决方案2：继承线程池ThreadPoolExecutor封装submit()
    public static class TraceTreadPoolExecutor extends ThreadPoolExecutor {
        public TraceTreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        //定义方法封装submit()
        public Runnable wrap(Runnable task, Exception exception) {
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception e) {
                        exception.printStackTrace();
                        throw e;
                    }
                }
            };
        }

        //重写submit()方法
        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task, new Exception("客户跟踪异常")));
        }
        //重写execute()方法

        @Override
        public void execute(Runnable command) {
            super.execute(wrap(command, new Exception("客户跟踪异常")));
        }
    }

    public static class DivideTask implements Runnable {
        private int x;
        private int y;

        public DivideTask(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + "计算" + x + "/" + y + "=" + (x / y));
        }
    }

    public static void main(String[] args) {
/*
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>());
*/
        TraceTreadPoolExecutor traceTreadPoolExecutor = new TraceTreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS
                , new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            //threadPoolExecutor.submit(new DivideTask(10, i));
            //submit不会抛出运行时异常，而execute会抛出运行时异常
            //解决办法1：把submit改为execute
            //threadPoolExecutor.execute(new DivideTask(10, i));
            //解决办法2：对线程池进行扩展，对submit()方法进行包装
            traceTreadPoolExecutor.submit(new DivideTask(10, i));
            //traceTreadPoolExecutor.execute(new DivideTask(10, i));
        }
    }
}
