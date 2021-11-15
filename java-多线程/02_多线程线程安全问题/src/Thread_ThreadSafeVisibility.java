import java.util.Random;

public class Thread_ThreadSafeVisibility {
    /**
     * 可见性测试
     */
    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        Thread t1 = new Thread(myTask);
        t1.start();

        Thread.sleep(1000);
        System.out.println("id编号= "+ Thread.currentThread().getId());
        //主线1秒钟之后取消子线程
        synchronized (myTask) {
            System.out.println(t1.getState());
            if(t1.getState().equals(Thread.State.TERMINATED)){
                System.out.println("t1 is terminated!");
                return;
            }
            myTask.cancel();
        }
        /**
         * 可能会出现在main线程当中调用了task.cancel()方法，把task变量修改为true
         * 可能存在子线程看不到main线程对toCancel做的修改，在子线程中toCancel一直为false
         * 导致子线程看不到main线程对toCancel变量修改的原因：
         * 1）JIT即时编译器会对run方法中的while循环进行优化，优化为：
         * if(!toCancel){
         *     while(true){
         *         if(doSomething()){
         *
         *         }
         *     }
         * }
         * 2）可能与计算机的存储系统有关，假设有两个cpu内核运行main线程和子线程
         * 运行子线程的cpu无法立即读取运行main线程cpu的数据
         */
    }

    static class MyTask implements Runnable {
        private boolean toCancel = false;

        @Override
        public void run() {
            while (!toCancel) {
                Thread.yield();
                    if (doSomthing()) {
                        break;
                    }
            }
            synchronized (this) {
                if (toCancel) {
                    System.out.println("任务被取消");
                } else {
                    System.out.println("任务正常执行");
                }
            }
        }

        private boolean doSomthing() {
            System.out.println("正在执行某个任务......");
            try {
                System.out.println("id编号= "+ Thread.currentThread().getId());
                Thread.sleep(new Random().nextInt(1000));//模拟执行任务的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        public void cancel() {
            System.out.println("subId编号= "+ Thread.currentThread().getId());
            toCancel = true;
            System.out.println("收到 取消线程的消息");
        }

    }
}
