import java.util.concurrent.locks.ReentrantLock;

public class Thread_isLockTest {
    /**
     * isLock()判断当前锁是否被线程所持有
     */
    static ReentrantLock lock = new ReentrantLock();

    static void sm() {
        try {
            if (lock.isLocked()) {
                System.out.println(Thread.currentThread().getName() + " lock is be held....." + lock.isLocked());
                Thread.sleep(2000);
            }
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " obtained lock");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sm();
                }
            }).start();
        }
    }
}
