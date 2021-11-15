import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_hasWaitersTest {
    /**
     * hasWaiters()查询是否有正在等待某condition的线程
     */
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    static void sm() {
        try {
            lock.lock();
            System.out.println("Are there any Threads waiting to the lock：" + lock.hasWaiters(condition) + " | waitersNums：" + lock.getWaitQueueLength(condition));
            System.out.println(Thread.currentThread().getName() + " obtained lock....");
            condition.await(new Random().nextInt(1000), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getName() + " overTime unlocked...");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
