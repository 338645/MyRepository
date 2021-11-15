import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_LockReentrantLockTest03 {
    /**
     * ReentrantLock的可重入性
     */
    static class SubThread extends Thread {
        private static Lock lock = new ReentrantLock();

        @Override
        public void run() {
/*
            if (lock.tryLock()) {
                try {
                    for (int i = 0; i < 100; i++) {
                        if (lock.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "-" + i);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
*/
            for (int i = 0; i < 100; i++) {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "-" + i);
                System.out.println(lock.tryLock());
                lock.unlock();
                lock.unlock();
                //可重入锁指可以反复获得，但获得多少次，释放也需要释放多少次。
            }
        }
    }

    public static void main(String[] args) {
        SubThread subThread1 = new SubThread();
        SubThread subThread = new SubThread();
        subThread1.start();
        subThread.start();
    }
}
