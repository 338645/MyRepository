import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_tryLockTest03 {
    /**
     * tryLock()方法的使用
     */
    static class Service {
        private static final ReentrantLock LOCK = new ReentrantLock();

        public void serviceMethod() {
            try {
                if (LOCK.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " obtained Lock....");
                    Thread.sleep(3000);
                } else {
                    System.out.println(Thread.currentThread().getName() + " doesn't obtained Lock...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (LOCK.isHeldByCurrentThread()) {
                    LOCK.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}
