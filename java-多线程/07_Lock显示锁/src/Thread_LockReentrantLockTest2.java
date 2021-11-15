import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_LockReentrantLockTest2 {
    /**
     * 使用lock同步不同方法中的同步代码块
     * lock锁在使用时经常在try代码块中获得锁
     * 在finally中释放锁
     */
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m1();
            }
        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                m2();
            }
        });
        thread1.start();
        thread.start();
    }

    static void m1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "--" + "m1" + "--" + System.currentTimeMillis());
            //System.out.println(lock.tryLock());
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static void m2() {
        try {
          /*  Thread.sleep(1000);
            lock.lockInterruptibly();*/
            //System.out.println(lock.tryLock());
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "--" + "m2" + "--" + System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
