import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_ReentrantLock_newConditionTest {
    /**
     * Condition的await()方法和signal()方法
     * 注意：不管是await()还是signal()方法都需要先持有锁对象才能调用
     */
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (lock.tryLock()) {//获得锁
                        System.out.println("method locked....");
                        System.out.println("method wait....");
                        condition.await();//线程等待
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        System.out.println("method end....");
                        lock.unlock();//释放锁
                    }
                }
            }
        });
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //不管是await还是signal在调用之前都需要持有相关的锁对象
        try {
            if (lock.tryLock()) {
                condition.signal();
            }
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
