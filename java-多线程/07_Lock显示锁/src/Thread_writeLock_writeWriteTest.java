import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Thread_writeLock_writeWriteTest {
    /**
     * 写写互斥演示
     */
    static class Service {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void write() {
            try {
                lock.writeLock().lock();
                System.out.println(Thread.currentThread().getName() + "申请到写锁，开始执行业务：" + System.currentTimeMillis());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.writeLock().isHeldByCurrentThread()) {
                    System.out.println(Thread.currentThread().getName() + "执行业务完毕，时间：" + System.currentTimeMillis());
                    lock.writeLock().unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.write();
                }
            }).start();
        }
    }
}
