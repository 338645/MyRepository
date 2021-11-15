import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_lockInterruptiblyTest {
    /**
     * lockInterruptibly演示：如果未被中断则获得锁，否则抛出异常
     */
    static class Service {
        private static Lock lock = new ReentrantLock();


        public void serviceMethod() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(100);
                System.out.println("获得锁");
                lock.lockInterruptibly();
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    new StringBuilder();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("释放锁");
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        Service service = new Service();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        });
        thread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
