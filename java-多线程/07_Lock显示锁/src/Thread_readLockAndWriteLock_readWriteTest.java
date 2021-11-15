import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Thread_readLockAndWriteLock_readWriteTest {
    /**
     * 读写互斥演示
     * 一个线程获得读锁时，写线程等待；一个线程获得写锁，读线程等待
     */
    static class Service {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void read() {
            try {
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "线程获得读锁，开始读取数据：" + System.currentTimeMillis());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "线程释放读锁，读取数据完毕：" + System.currentTimeMillis());
                lock.readLock().unlock();
            }
        }

        public void write() {
            try {
                lock.writeLock().lock();
                System.out.println(Thread.currentThread().getName() + "线程获得写锁，开始更新数据：" +
                        System.currentTimeMillis());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.writeLock().isHeldByCurrentThread()) {
                    System.out.println(Thread.currentThread().getName() + "线程释放写锁，更新数据完毕：" + System.currentTimeMillis());
                    lock.writeLock().unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        });
        thread1.start();
        Thread.sleep(10);
        thread.start();
    }
}
