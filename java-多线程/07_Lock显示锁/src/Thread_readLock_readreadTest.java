import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Thread_readLock_readreadTest {
    /**
     * 读读共享演示
     */
    static class Service {
        static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void read() {
            try {
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "线程获得读锁的时间：" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "线程结束的时间：" + System.currentTimeMillis());
                lock.readLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.read();
                }
            }).start();
        }
        /**
         * 多个线程几乎可以同时获得读锁执行lock()后面的代码
         */
    }
}
