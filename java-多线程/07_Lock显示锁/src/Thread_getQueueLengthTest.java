import java.util.concurrent.locks.ReentrantLock;

public class Thread_getQueueLengthTest {
    /**
     * getQueueLength()返回正等待线程的预估数
     */
    static  ReentrantLock lock = new ReentrantLock();

    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " obtained lock.....");
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    //lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            new SubThread().start();
        }
        Thread.sleep(3000);
        System.out.println(lock.getQueueLength());

    }
}
