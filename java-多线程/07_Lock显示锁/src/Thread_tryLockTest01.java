import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_tryLockTest01 {
    /**
     * tryLock()方法的基本使用1
     */
    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    static class TimeInterLock implements Runnable {
        private int lockNums;

        public TimeInterLock(int lockNums) {
            this.lockNums = lockNums;
        }

        @Override
        public void run() {
            try {
                if (lockNums % 2 == 1) {
                    lock1.lock();
                    System.out.println(Thread.currentThread().getName()+" obtain lock1");
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName()+" obtain lock2");
                } else {
                    lock2.tryLock(3,TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName()+" obtain lock2");
                    lock1.tryLock(3,TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName()+" obtain lock1");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TimeInterLock(1)).start();
        new Thread(new TimeInterLock(2)).start();
    }

}
