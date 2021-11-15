import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_tryLockAndDeadLockTest {
    /**
     * 使用tryLock()可以避免死锁
     * tryLock()避免死锁时如果执行业务时间太长可能难以正常运行
     */
    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    static class InterLock implements Runnable {
        private int lockNums;

        public InterLock(int lockNums) {
            this.lockNums = lockNums;
        }

        @Override
        public void run() {
            try {
                if (lockNums % 2 == 1) {
                    while (true) {
                        Thread.sleep(new Random().nextInt(100));
                        if (lock2.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + " obtained lock2.....");
                            Thread.sleep(3000);
                            if (lock1.tryLock()) {
                                System.out.println(Thread.currentThread().getName() + " obtained lock1 and lock2, The work is finished!");
                                return;
                            }else {
                                lock2.unlock();
                                System.out.println(Thread.currentThread().getName() + " obtained lock1 and lock2, The work is finished!");
                                return;
                            }
                        }
                    }
                } else {
                    //Thread.sleep(new Random().nextInt(100));
                    while (true) {
                        if (lock1.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + " obtained lock1.....");
                            Thread.sleep(3000);
                            if (lock2.tryLock()) {
                                System.out.println(Thread.currentThread().getName() + " obtained lock1 and lock2, The work is finished!");
                                return;
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new InterLock(1));
        Thread t2 = new Thread(new InterLock(2));
        t1.start();
        t2.start();
        //运行后使用tryLock方法尝试获得锁不会傻傻的等待
    }
}
