import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_tryLockTest02 {
    /**
     *tryLock()的基本用法
     */
    static ReentrantLock lock = new ReentrantLock();

    static class TimeLock implements Runnable {
        @Override
        public void run() {
            try {
                //使用tryLock的线程可以在约定时间内获得锁
                if (lock.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " obtained lock" + ", and is executing program.....");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " is executed");
                } else {
                    System.out.println(Thread.currentThread().getName() + " doesn't obtain lock......");
                    //假设线程在等待3秒之后还没获得锁的话就会放弃获得锁
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new TimeLock());
        Thread thread1 = new Thread(new TimeLock());
        thread.start();
        thread1.start();
    }
}
