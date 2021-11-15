import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_LockReentrantLockTest {
    /**
     * ReentrantLock的基本使用
     * Lock接口的实现实例对象.lock()：获得锁
     * Lock接口的实现类.unlock()：释放锁
     */
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new MyRunnable()).start();
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁....");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放锁....");
            lock.unlock();
        }
    }

}
