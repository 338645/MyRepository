import java.util.concurrent.locks.ReentrantLock;

public class Thread_ReentrantLockAndFairLock {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new FiarLock.FairThread().start();
            //new UnfairLock.UnfairThread().start();
        }
    }
}

class FiarLock {
    static ReentrantLock fairLock = new ReentrantLock(true);

    static class FairThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
/*
                    if(fairLock.tryLock()){
                        //使用tryLock因为没有进入阻塞列队，所以会使公平锁失效
                        System.out.println(Thread.currentThread().getName() + " obtained lock...");
                    }
*/
                    fairLock.lock();
                    System.out.println(Thread.currentThread().getName() + " obtained lock...");
                } finally {
                    if (fairLock.isHeldByCurrentThread()) {
                        fairLock.unlock();
                    }
                }
            }
        }
    }
}

class UnfairLock {
    static ReentrantLock unfairLock = new ReentrantLock();

    static class UnfairThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    while (true) {
                        if (unfairLock.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + " obtained Lock......");
                            break;
                        }
                    }
                } finally {
                    if (unfairLock.isHeldByCurrentThread()) {
                        unfairLock.unlock();
                    }
                }
            }
        }
    }

}
