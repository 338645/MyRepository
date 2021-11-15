import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_isFair_And_isHeldByCurrntThread_Test {
    /**
     * isFair()方法判断是否为公平锁
     * isHeldByCurrentThread()方法判断锁是否被当前线程所持有
     *
     * @param args
     */
    static class Service {
        private ReentrantLock lock;

        public Service(boolean fair) {
            this.lock = new ReentrantLock(fair);
        }

        public void serviceMethod() {
            try {
                System.out.println(Thread.currentThread().getName() + "线程获得的锁是否为公平锁：" + lock.isFair() +
                        "，线程是否已经获得过该锁:" + lock.isHeldByCurrentThread());
                if (lock.isFair()) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "线程调用lock后是否持有锁：" + lock.isHeldByCurrentThread());
                }
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }

    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt();
                new Service(num % 2 == 0 ? true : false).serviceMethod();

            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(r, "thread-" + i).start();
        }
    }
}
