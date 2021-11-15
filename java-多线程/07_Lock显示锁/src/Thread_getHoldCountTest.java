import java.util.concurrent.locks.ReentrantLock;

public class Thread_getHoldCountTest {
    /**
     * getHoldCount()方法可以返回当前线程调用lock()方法的次数
     */
    static ReentrantLock lock = new ReentrantLock();

    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " obtain " + i + " lock.....");
                    lock.lock();
                }
            } finally {
                System.out.println(lock.getHoldCount());
                for (int i = 0; i < lock.getHoldCount(); i++) {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName() + " release " + i + " lock.....");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new SubThread().start();
    }

}
