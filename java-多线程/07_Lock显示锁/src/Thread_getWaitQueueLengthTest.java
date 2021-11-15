import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_getWaitQueueLengthTest {
    /**
     * getWaitQueueLength()返回与Condition条件相关的等待线程预估数
     */

    static class Service {
        static ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();//返回锁给定的Condition

        public void waitMethod() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "进入等待前，现在该Condition条件上等待的线程预估数：" + lock.getWaitQueueLength(condition));
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }

        public void notifyMethod() {
            try {
                lock.lock();
                condition.signalAll();
                System.out.println(Thread.currentThread().getName() + "唤醒所有的等待后，现在该Condition条件上等待的线程预估数：" + lock.getWaitQueueLength(condition));
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        Thread.sleep(3000);
        service.notifyMethod(); //唤醒所有线程
    }
}
