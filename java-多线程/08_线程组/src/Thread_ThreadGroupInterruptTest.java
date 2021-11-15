public class Thread_ThreadGroupInterruptTest {
    /**
     * 线程组的批量中断interrupt()
     */
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程开始循环：" + System.currentTimeMillis());
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "线程正在循环......");
/*                  中断睡眠中的线程会中断睡眠，并且消除中断标志
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
*/
                }
                System.out.println(Thread.currentThread().getName() + "循环结束，线程被中断：" + System.currentTimeMillis());
            }
        };
        ThreadGroup group = new ThreadGroup("group");
        for (int i = 0; i < 5; i++) {
            new Thread(group, r).start();
        }
        Thread.sleep(3000);
        group.interrupt();
    }
}
