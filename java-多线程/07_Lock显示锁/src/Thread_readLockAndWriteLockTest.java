import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Thread_readLockAndWriteLockTest {
    /**
     * 读写锁的演示
     * readLock()：读锁;获得读锁格式：readLock().lock()
     * writeLock()：写锁;获得写锁格式：writeLock().lock()
     */
    static class Service {
        //private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);//使用公平锁
        private int num = 0;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public ReentrantReadWriteLock getLock() {
            return lock;
        }
    }

    static class ReadThread extends Thread {
        private Service service;
        private ReentrantReadWriteLock lock;

        public ReadThread(Service service) {
            this.service = service;
            lock = service.getLock();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (!lock.isWriteLocked()) {
                        lock.readLock().lock();
                        System.out.println(Thread.currentThread().getName() + " num is：" + service.getNum() +
                                ", Executed Time：" + System.currentTimeMillis());
                        Thread.sleep(3000);
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "睡眠完毕....");
                lock.readLock().unlock();
            }
        }
    }

    static class WriteThread extends Thread {
        private Service service;
        private ReentrantReadWriteLock lock;

        public WriteThread(Service service) {
            this.service = service;
            this.lock = service.getLock();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName() + "线程获得写锁：" + System.currentTimeMillis());
                    service.setNum(service.getNum() + 1);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock.writeLock().isHeldByCurrentThread()) {
                        System.out.println(Thread.currentThread().getName() + "线程加1操作完成,值：" + service.getNum() + " ，时间：" + System.currentTimeMillis());
                        lock.writeLock().unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Service s = new Service();
        /*//读读共享
        for (int i = 0; i < 5; i++) {
            new ReadThread(s).start();
        }
        //写写互斥
        for (int i = 0; i < 2; i++) {
            new WriteThread(s).start();
        }*/

        //读写互斥
        //有一个线程获得读锁之后，其他线程不可以获得写锁
        //一个线程获得写锁之后，其他线程不可以获得读锁
        ReadThread readThread = new ReadThread(s);
        WriteThread writeThread = new WriteThread(s);
        readThread.start();
        writeThread.start();
    }
}
